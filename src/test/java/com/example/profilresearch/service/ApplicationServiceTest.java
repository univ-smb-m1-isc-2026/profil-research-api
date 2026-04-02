package com.example.profilresearch.service;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.dto.ApplicationResponse;
import com.example.profilresearch.dto.QuestionApplicationRequest;
import com.example.profilresearch.dto.QuestionApplicationResponse;
import com.example.profilresearch.entity.*;
import com.example.profilresearch.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private JobOfferService jobOfferService;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuestionApplicationService questionApplicationService;

    @InjectMocks
    private ApplicationService applicationService;

    @Test
    void testGetApplicationByJobOffer() {
        String idJob = "1";
        Long jobId = 1L;
        
        Application application = new Application();
        application.setId(10L);
        application.setFirstname("John");
        application.setLastname("Doe");
        application.setMail("john.doe@example.com");

        when(applicationRepository.findAllByJobOffer_Id(jobId)).thenReturn(List.of(application));

        List<ApplicationResponse> result = applicationService.getApplicationByJobOffer(idJob);

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstname());
        assertEquals(jobId, result.get(0).getId_job_offer());
    }

    @Test
    void testGetAllQuestionApplication() {
        String appliIdStr = "10";
        Long appliId = 10L;

        QuestionApplication qa = new QuestionApplication();
        qa.setId(100L);
        qa.setResponses(List.of("Ma réponse"));
        
        when(questionApplicationService.getAllQuestionApplicationByApplication(appliId)).thenReturn(List.of(qa));

        List<QuestionApplicationResponse> result = applicationService.getAllQuestionApplication(appliIdStr);

        assertEquals(1, result.size());
        assertEquals(List.of("Ma réponse"), result.get(0).getResponses());
        assertEquals(appliId, result.get(0).getId_application());
    }

    @Test
    void testDeleteById() {
        String idStr = "10";
        Long id = 10L;
        
        QuestionApplication qa = new QuestionApplication();
        qa.setId(100L);
        
        when(questionApplicationService.getAllQuestionApplicationByApplication(id)).thenReturn(List.of(qa));

        applicationService.deleteById(idStr);

        verify(questionApplicationService).deleteById(100L);
        verify(applicationRepository).deleteById(id);
    }

    @Test
    void testCreateApplication() {
        ApplicationRequest request = new ApplicationRequest();
        request.setFirstname("John");
        request.setLastname("Doe");
        request.setMail("john.doe@example.com");
        request.setJobOffer_id(1L);
        
        QuestionApplicationRequest qRequest = new QuestionApplicationRequest();
        qRequest.setId_question(5L);
        qRequest.setResponses(List.of("Response"));
        request.setResponses(List.of(qRequest));

        JobOffer jo = new JobOffer();
        jo.setId(1L);
        
        Question q = new Question();
        q.setId(5L);
        
        Application savedApp = new Application();
        savedApp.setId(10L);

        when(jobOfferService.getJobOfferById(1L)).thenReturn(Optional.of(jo));
        when(applicationRepository.save(any(Application.class))).thenReturn(savedApp);
        when(questionService.getQuestionById(5L)).thenReturn(Optional.of(q));

        String result = applicationService.createApplication(request);

        assertEquals("Application ajouté", result);
        verify(applicationRepository).save(any(Application.class));
        verify(questionApplicationService).createQuestionApplication(q, savedApp, List.of("Response"));
    }

    @Test
    void testCreateApplicationJobNotFound() {
        ApplicationRequest request = new ApplicationRequest();
        request.setJobOffer_id(1L);
        
        when(jobOfferService.getJobOfferById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> applicationService.createApplication(request));
    }
}
