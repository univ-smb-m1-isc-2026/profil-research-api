package com.example.profilresearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

@CrossOrigin(origins = {"https://psjob.oups.net", "http://localhost:3000"})
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/hello")
    public String hello() {
        var list = new ArrayList<>();
        logger.info("Serving {} facts", list.size());
        return "Bonjour depuis Spring Boot !";
    }
}

