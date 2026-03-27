package com.example.profilresearch.controller;

import com.example.profilresearch.dto.JobOfferRequest;
import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joboffer") // start of all requests for this file
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @GetMapping("/getAll")
    public List<JobOffer> getAllJobOffer() {
        return jobOfferService.getAllJobOffer();
    }

    @GetMapping("/getAllPublic")
    public List<JobOffer> getPublicJobOffer() {
        return jobOfferService.getPublicJobOffer();
    }

    @DeleteMapping("/delete/{jobOfferId}")
    public void deleteById(@PathVariable String jobOfferId) {
        jobOfferService.deleteById(jobOfferId);
    }

    @PostMapping("/addJobOffer")
    public String addJobOffer(@RequestBody JobOfferRequest request) {
        return jobOfferService.createJobOffer(request);
    }

    @PutMapping("/editIsPublic/{idJobOffer}")
    public String editParking(@PathVariable Long idJobOffer) {
        return jobOfferService.updateIsPublic(idJobOffer);
    }
}
