package com.example.profilresearch.config;

import com.example.profilresearch.entity.User;
import com.example.profilresearch.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        String adminEmail = "test.univ.savoie@gmail.com";
        if (userRepository.findById(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setMail(adminEmail);
            admin.setFirstname("Admin");
            admin.setSurname("System");
            admin.setProvider("system");
            userRepository.save(admin);
            System.out.println("Utilisateur admin créé par défaut : " + adminEmail);
        }
    }
}
