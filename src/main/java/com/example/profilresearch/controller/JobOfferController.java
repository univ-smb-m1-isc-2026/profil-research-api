package com.example.profilresearch.controller;

import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joboffer") // start of all requests for this file
@RequiredArgsConstructor // j'ai fais confiance à lohan
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @GetMapping
    public List<JobOffer> getAllJobOffer() {
        return jobOfferService.getAllJobOffer();
    }

    @GetMapping("candidate")
    public List<JobOffer> getPublicJobOffer() {
        return jobOfferService.getPublicJobOffer();
    }

    @DeleteMapping("/delete/{jobOfferId}")
    public void deleteById(@PathVariable String jobOfferId) {
        jobOfferService.deleteById(jobOfferId);
    }
}
