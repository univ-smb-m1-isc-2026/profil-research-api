package com.example.profilresearch.service;

import com.example.profilresearch.dto.JobOfferRequest;
import com.example.profilresearch.dto.QuestionJobOfferResponse;
import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.entity.QuestionJobOffer;
import com.example.profilresearch.repository.JobOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobOfferServiceTest {

    @Mock
    private JobOfferRepository jobOfferRepository;

    @Mock
    private QuestionService questionService;

    @Mock
    private QuestionJobOfferService questionJobOfferService;

    @InjectMocks
    private JobOfferService jobOfferService;

    @Test
    void testGetAllJobOffer() {
        // Arrange
        JobOffer offer1 = new JobOffer();
        offer1.setTitle("Developpeur Java");
        JobOffer offer2 = new JobOffer();
        offer2.setTitle("Developpeur React");
        
        when(jobOfferRepository.findAll()).thenReturn(Arrays.asList(offer1, offer2));

        // Act
        List<JobOffer> result = jobOfferService.getAllJobOffer();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Developpeur Java", result.get(0).getTitle());
    }

    @Test
    void testGetAllJobOfferWhenNoJobPresent() {
        // Arrange
        when(jobOfferRepository.findAll()).thenReturn(List.of());

        // Act
        List<JobOffer> result = jobOfferService.getAllJobOffer();

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void testGetPublicJobOffer() {
        JobOffer publicOffer = new JobOffer();
        publicOffer.setIsPublic(true);
        when(jobOfferRepository.findByIsPublicTrue()).thenReturn(List.of(publicOffer));

        List<JobOffer> result = jobOfferService.getPublicJobOffer();

        assertEquals(1, result.size());
        assertTrue(result.get(0).isPublic());
    }

    @Test
    void testDeleteById() {
        String id = "1";
        Long longId = 1L;
        List<QuestionJobOffer> qjos = new ArrayList<>();
        QuestionJobOffer qjo = new QuestionJobOffer();
        qjo.setId(10L);
        qjos.add(qjo);

        when(questionJobOfferService.getAllQuestionJobOfferByJobOffer(longId)).thenReturn(qjos);

        jobOfferService.deleteById(id);

        verify(questionJobOfferService, times(1)).deleteById(10L);
        verify(jobOfferRepository, times(1)).deleteById(longId);
    }

    @Test
    void testCreateJobOffer() {
        JobOfferRequest request = new JobOfferRequest();
        request.setTitle("New Job");
        request.setId_question(List.of(1L));
        
        JobOffer savedJob = new JobOffer();
        savedJob.setId(1L);
        
        Question question = new Question();
        question.setId(1L);

        when(jobOfferRepository.save(any(JobOffer.class))).thenReturn(savedJob);
        when(questionService.getQuestionById(1L)).thenReturn(Optional.of(question));

        String result = jobOfferService.createJobOffer(request);

        assertEquals("JobOffer ajouté", result);
        verify(jobOfferRepository).save(any(JobOffer.class));
        verify(questionJobOfferService).createQuestionJobOffer(question, savedJob, 0);
    }

    @Test
    void testGetJobOfferById() {
        Long id = 1L;
        JobOffer jo = new JobOffer();
        jo.setId(id);
        
        when(jobOfferRepository.findById(id)).thenReturn(Optional.of(jo));

        Optional<JobOffer> result = jobOfferService.getJobOfferById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testUpdateIsPublic() {
        Long id = 1L;
        JobOffer jo = new JobOffer();
        jo.setId(id);
        jo.setIsPublic(false);
        
        when(jobOfferRepository.findById(id)).thenReturn(Optional.of(jo));

        String result = jobOfferService.updateIsPublic(id);

        assertEquals("JobOffer modifié", result);
        assertTrue(jo.isPublic());
        verify(jobOfferRepository).save(jo);
    }

    @Test
    void testUpdateIsPublicNotFound() {
        Long id = 1L;
        when(jobOfferRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> jobOfferService.updateIsPublic(id));
    }

    @Test
    void testGetAllQuestionJobOffer() {
        String id = "1";
        Long longId = 1L;
        
        JobOffer jo = new JobOffer();
        jo.setId(longId);

        Question q = new Question();
        q.setId(5L);

        QuestionJobOffer qjo = new QuestionJobOffer();
        qjo.setId(10L);
        qjo.setQuestion_number(1);
        qjo.setId_question(q);
        qjo.setId_job_offer(jo);
        
        when(questionJobOfferService.getAllQuestionJobOfferByJobOffer(longId)).thenReturn(List.of(qjo));

        List<QuestionJobOfferResponse> result = jobOfferService.getAllQuestionJobOffer(id);

        assertEquals(1, result.size());
        assertEquals(10L, result.get(0).getId());
        assertEquals(1, result.get(0).getQuestion_number());
        assertEquals(q, result.get(0).getId_question());
        assertEquals(longId, result.get(0).getId_job_offer());
    }
}
