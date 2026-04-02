package com.example.profilresearch.controller;

import com.example.profilresearch.entity.Invitation;
import com.example.profilresearch.repository.InvitationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

    private final InvitationRepository invitationRepository;

    public InvitationController(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateInvitationLink() {
        // En pratique, vous devriez vérifier ici que l'utilisateur connecté 
        // a les droits (ex: rôle ADMIN), mais pour le MVP on laisse sous /api/ 
        // qui est déjà protégé par JWT.

        Invitation invitation = new Invitation();
        invitation.setExpirationDate(LocalDateTime.now().plusDays(7)); // Valide 7 jours
        
        invitationRepository.save(invitation);

        // Lien de l'application frontend 
        String link = "http://localhost:3000/invite?token=" + invitation.getToken();
        
        return ResponseEntity.ok(link);
    }
}
