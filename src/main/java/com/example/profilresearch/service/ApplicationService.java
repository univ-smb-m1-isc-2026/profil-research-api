package com.example.profilresearch.service;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.dto.ApplicationResponse;
import com.example.profilresearch.dto.QuestionApplicationResponse;
import com.example.profilresearch.entity.*;
import com.example.profilresearch.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ApplicationRepository applicationRepository;
    private final JobOfferService jobOfferService;
    private final QuestionService questionService;
    private final QuestionApplicationService questionApplicationService;

    public List<ApplicationResponse> getApplicationByJobOffer(String id) {
        Long jobOfferId = Long.parseLong(id);
        List<Application> appli = applicationRepository.findAllByJobOffer_Id(jobOfferId);

        List<ApplicationResponse> applisRes = new ArrayList<ApplicationResponse>();
        for (Application application : appli) {
            ApplicationResponse app = new ApplicationResponse();
            app.setId(application.getId());
            app.setFirstname(application.getFirstname());
            app.setLastname(application.getLastname());
            app.setMail(application.getMail());
            app.setId_job_offer(jobOfferId);
            applisRes.add(app);
        }
        return applisRes;
    }

    public List<QuestionApplicationResponse> getAllQuestionApplication(String appliId){
        Long applicationId = Long.parseLong(appliId);
        List<QuestionApplication> qa = questionApplicationService.getAllQuestionApplicationByApplication(applicationId);
        List<QuestionApplicationResponse> qar = new ArrayList<QuestionApplicationResponse>();
        for (QuestionApplication questionApplication : qa) {
            QuestionApplicationResponse qari = new QuestionApplicationResponse();
            qari.setId(questionApplication.getId());
            qari.setId_application(applicationId);
            qari.setId_question(questionApplication.getId_question());
            qari.setResponses(questionApplication.getResponses());
            qar.add(qari);
        }
        return qar;
    }

    public void deleteById(String id) {
        Long applicationId = Long.parseLong(id);
        logger.info("Deleting Application with ID: {}", applicationId);
        List<QuestionApplication> qa = questionApplicationService.getAllQuestionApplicationByApplication(applicationId);
        for (QuestionApplication questionApplication : qa) {
            questionApplicationService.deleteById(questionApplication.getId());
        }
        applicationRepository.deleteById(applicationId);
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
