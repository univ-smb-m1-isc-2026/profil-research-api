package com.example.profilresearch.service;

import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    public List<JobOffer> getAllJobOffer() {
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> getPublicJobOffer() {
        return jobOfferRepository.findByIsPublicTrue();
    }
}
