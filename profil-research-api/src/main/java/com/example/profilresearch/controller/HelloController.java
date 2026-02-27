package com.example.profilresearch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // vérifier l'utilité
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Bonjour depuis Spring Boot !";
    }
}

