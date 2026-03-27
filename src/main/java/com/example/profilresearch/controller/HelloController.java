package com.example.profilresearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"https://psjob.oups.net", "http://localhost:3000"})
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Bonjour depuis Spring Boot !";
    }
}

