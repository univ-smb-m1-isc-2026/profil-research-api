package com.example.profilresearch.controller;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.dto.ApplicationResponse;
import com.example.profilresearch.entity.Application;
import com.example.profilresearch.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application") // start of all requests for this file
@RequiredArgsConstructor
public class ApplicationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationService applicationService;

    @GetMapping("/getApplicationByJobOffer/{jobOfferId}")
    public List<ApplicationResponse> getApplicationByJobOffer(@PathVariable String jobOfferId) {
        logger.info("Fetching applications for job offer ID: {}", jobOfferId);
        return applicationService.getApplicationByJobOffer(jobOfferId);
    }

    @DeleteMapping("/delete/{applicationId}")
    public void deleteById(@PathVariable String applicationId) {
        logger.info("Deleting application with ID: {}", applicationId);
        applicationService.deleteById(applicationId);
    }

    @PostMapping("/addApplication")
    public String addApplication(@RequestBody ApplicationRequest request) {
        logger.info("Received new application from: {} {}", request.getFirstname(), request.getLastname());
        return applicationService.createApplication(request);
    }
}