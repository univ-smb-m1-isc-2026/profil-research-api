package com.example.profilresearch.service;

import com.example.profilresearch.dto.JobOfferRequest;
import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobOfferService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JobOfferRepository jobOfferRepository;

    public List<JobOffer> getAllJobOffer() {
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> getPublicJobOffer() {
        return jobOfferRepository.findByIsPublicTrue();
    }

    public void deleteById(String jobOfferId) {
        Long JOId = Long.parseLong(jobOfferId);
        logger.info("Deleting JobOffer with ID: {}", JOId);
        jobOfferRepository.deleteById(JOId);
        // we delete also all the applications and the questions with ON DELETE CASCADE
        // in the creation of tab Application and QuestionJobOffer
    }

    public String createJobOffer(JobOfferRequest request) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setTitle(request.getTitle());
        jobOffer.setDescription(request.getDescription());
        jobOffer.setIsPublic(request.getIsPublic());
        jobOffer.setContractType(request.getContractType());
        jobOffer.setLocation(request.getLocation());
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = localDate.format(formatter);
        jobOffer.setDate(formattedString);
        jobOfferRepository.save(jobOffer);
        logger.info("JobOffer created: {}", jobOffer.getTitle());

        return "JobOffer ajouté";
    }

    public Optional<JobOffer> getJobOfferById(Long id){
        return jobOfferRepository.findById(id);
    }

    public String updateIsPublic(Long idJobOffer){
        JobOffer jo = this.getJobOfferById(idJobOffer)
                .orElseThrow(() -> new RuntimeException("Job Offer not found"));
        jo.setIsPublic(!jo.isPublic());
        jobOfferRepository.save(jo);
        logger.info("JobOffer {} set to public: {}", idJobOffer, jo.isPublic());
        return "JobOffer modifié";
    }
}
