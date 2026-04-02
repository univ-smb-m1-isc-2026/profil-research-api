package com.example.profilresearch.service;

import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.entity.QuestionJobOffer;
import com.example.profilresearch.repository.QuestionJobOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionJobOfferServiceTest {

    @Mock
    private QuestionJobOfferRepository questionJobOfferRepository;

    @InjectMocks
    private QuestionJobOfferService questionJobOfferService;

    @Test
    void testCreateQuestionJobOffer() {
        // Arrange
        Question question = new Question();
        question.setId(1L);
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1L);
        Integer index = 0;

        // Act
        questionJobOfferService.createQuestionJobOffer(question, jobOffer, index);

        // Assert
        verify(questionJobOfferRepository, times(1)).save(any(QuestionJobOffer.class));
    }

    @Test
    void testGetAllQuestionJobOfferByJobOffer() {
        // Arrange
        Long jobId = 1L;
        QuestionJobOffer qjo = new QuestionJobOffer();
        qjo.setId(1L);
        when(questionJobOfferRepository.findQuestionJobOfferById_job_offer(jobId)).thenReturn(List.of(qjo));

        // Act
        List<QuestionJobOffer> result = questionJobOfferService.getAllQuestionJobOfferByJobOffer(jobId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(questionJobOfferRepository, times(1)).findQuestionJobOfferById_job_offer(jobId);
    }

    @Test
    void testDeleteById() {
        // Arrange
        Long qjoId = 1L;

        // Act
        questionJobOfferService.deleteById(qjoId);

        // Assert
        verify(questionJobOfferRepository, times(1)).deleteById(qjoId);
    }
}
