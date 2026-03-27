package com.example.profilresearch.controller;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.entity.Application;
import com.example.profilresearch.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application") // start of all requests for this file
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/getApplicationByJobOffer/{jobOfferId}")
    public List<Application> getApplicationByJobOffer(@PathVariable String jobOfferId) {
        return applicationService.getApplicationByJobOffer(jobOfferId);
    }

    @DeleteMapping("/delete/{applicationId}")
    public void deleteById(@PathVariable String applicationId) {
        applicationService.deleteById(applicationId);
    }

    @PostMapping("/addApplication")
    public String addApplication(@RequestBody ApplicationRequest request) {
        return applicationService.createApplication(request);
    }
}