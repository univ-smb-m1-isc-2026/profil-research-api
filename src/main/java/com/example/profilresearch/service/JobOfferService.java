package com.example.profilresearch.service;

import com.example.profilresearch.dto.JobOfferRequest;
import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteById(String jobOfferId) {
        Long JOId = Long.parseLong(jobOfferId);
        jobOfferRepository.deleteById(JOId);
        // we delete also all the applications and the questions with ON DELETE CASCADE
        // in the creation of tab Application and QuestionJobOffer
    }

    public String createJobOffer(JobOfferRequest request) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setTitle(request.getTitle());
        jobOffer.setDescription(request.getDescription());
        jobOffer.setIsPublic(request.getIsPublic());
        jobOfferRepository.save(jobOffer);

        return "JobOffer ajouté";
    }

    public Optional<JobOffer> getJobOfferById(Long id){
        return jobOfferRepository.findById(id);
    }
}
