package com.example.profilresearch.service;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.entity.Application;
import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationRepository applicationRepository;
    private final JobOfferService jobOfferService;
    private final QuestionService questionService;
    private final QuestionApplicationService questionApplicationService;

    public List<Application> getApplicationByJobOffer(String id) {
        Long jobOfferId = Long.parseLong(id);
        return applicationRepository.findAllByJobOffer_Id(jobOfferId);
    }

    public void deleteById(String id) {
        Long applicationId = Long.parseLong(id);
        logger.info("Deleting Application with ID: {}", applicationId);
        applicationRepository.deleteById(applicationId);
        // we delete also all the answers to the questions of the job offer with ON DELETE CASCADE in the creation of the tab QuestionApplication
    }

    public String createApplication(ApplicationRequest request) {
        Application application = new Application();
        application.setMail(request.getMail());
        application.setLastname(request.getLastname());
        application.setFirstname(request.getFirstname());

        JobOffer jo = jobOfferService.getJobOfferById(request.getJobOffer_id())
                .orElseThrow(() -> new RuntimeException("Job Offer not found"));
        application.setJobOffer(jo);

        logger.info("Application created for {} {} on JobOffer {}", application.getFirstname(), application.getLastname(), jo.getId());
        Application appli = applicationRepository.save(application);

        for(int i = 0; i < request.getResponses().size(); i++){
            Question question = questionService.getQuestionById(request.getResponses().get(i).getId_question())
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            questionApplicationService.createQuestionApplication(question, appli, request.getResponses().get(i).getResponses());
        }

        return "Application ajouté";
    }
}
