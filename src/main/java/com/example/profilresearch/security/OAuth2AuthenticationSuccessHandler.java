package com.example.profilresearch.security;

import com.example.profilresearch.entity.Invitation;
import com.example.profilresearch.entity.User;
import com.example.profilresearch.repository.InvitationRepository;
import com.example.profilresearch.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final InvitationRepository invitationRepository;

    @Value("${app.frontend.oauth-redirect:http://localhost:3000/oauth2/redirect}")
    private String redirectUri;

    @Autowired
    public OAuth2AuthenticationSuccessHandler(JwtTokenProvider tokenProvider, 
                                              UserRepository userRepository,
                                              InvitationRepository invitationRepository) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String providerId = oAuth2User.getName(); // usually Google ID

        Optional<User> userOptional = userRepository.findById(email);
        
        // Nouvelle connexion / creation de compte avec token d'invitation
        if (userOptional.isEmpty()) {
            String inviteToken = getCookieValue(request, "invite_token");

            // Base URL extracted from redirectUri to avoid hardcoding localhost
            String baseUrl = redirectUri.replace("/oauth2/redirect", "");

            if (inviteToken == null) {
                getRedirectStrategy().sendRedirect(request, response, baseUrl + "/login?error=Lien_invitation_manquant");
                return;
            }

            Optional<Invitation> inviteOpt = invitationRepository.findByToken(inviteToken);
            if (inviteOpt.isEmpty() || inviteOpt.get().isUsed() || 
                (inviteOpt.get().getExpirationDate() != null && inviteOpt.get().getExpirationDate().isBefore(LocalDateTime.now()))) {
                getRedirectStrategy().sendRedirect(request, response, baseUrl + "/login?error=Lien_invitation_invalide_ou_expire");
                return;
            }

            // Créer l'utilisateur
            User newUser = new User();
            newUser.setMail(email);
            if (name != null) {
                String[] parts = name.split(" ");
                newUser.setFirstname(parts[0]);
                if (parts.length > 1) newUser.setSurname(parts[1]);
            }
            newUser.setProvider("google");
            newUser.setProviderId(providerId);
            userRepository.save(newUser);

            // Marquer l'invit utilisée
            Invitation invitation = inviteOpt.get();
            invitation.setUsed(true);
            invitationRepository.save(invitation);
        }

        // Générer le JWT
        String token = tokenProvider.generateToken(email);

        // Nettoyer le cookie
        clearCookie(response, "invite_token");

        // Rediriger avec le token
        String targetUrl = redirectUri + "?token=" + token;
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void clearCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
