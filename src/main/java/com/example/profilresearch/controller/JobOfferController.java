package com.example.profilresearch.controller;

import com.example.profilresearch.dto.JobOfferRequest;
import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joboffer") // start of all requests for this file
@RequiredArgsConstructor
public class JobOfferController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JobOfferService jobOfferService;

    @GetMapping("/getAll")
    public List<JobOffer> getAllJobOffer() {
        logger.info("Fetching all job offers");
        return jobOfferService.getAllJobOffer();
    }

    @GetMapping("/getAllPublic")
    public List<JobOffer> getPublicJobOffer() {
        logger.info("Fetching all public job offers");
        return jobOfferService.getPublicJobOffer();
    }

    @DeleteMapping("/delete/{jobOfferId}")
    public void deleteById(@PathVariable String jobOfferId) {
        logger.info("Deleting job offer with ID: {}", jobOfferId);
        jobOfferService.deleteById(jobOfferId);
    }

    @PostMapping("/addJobOffer")
    public String addJobOffer(@RequestBody JobOfferRequest request) {
        logger.info("Adding new job offer: {}", request.getTitle());
        return jobOfferService.createJobOffer(request);
    }

    @PutMapping("/editIsPublic/{idJobOffer}")
    public String editParking(@PathVariable Long idJobOffer) {
        logger.info("Toggling visibility for job offer ID: {}", idJobOffer);
        return jobOfferService.updateIsPublic(idJobOffer);
    }
}
