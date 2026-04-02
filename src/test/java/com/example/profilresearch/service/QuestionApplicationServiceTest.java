package com.example.profilresearch.service;

import com.example.profilresearch.entity.Application;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.entity.QuestionApplication;
import com.example.profilresearch.repository.QuestionApplicationRepository;
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
class QuestionApplicationServiceTest {

    @Mock
    private QuestionApplicationRepository questionApplicationRepository;

    @InjectMocks
    private QuestionApplicationService questionApplicationService;

    @Test
    void testCreateQuestionApplication() {
        // Arrange
        Question question = new Question();
        question.setId(1L);
        Application application = new Application();
        application.setId(1L);
        List<String> responses = List.of("Response 1", "Response 2");

        // Act
        questionApplicationService.createQuestionApplication(question, application, responses);

        // Assert
        verify(questionApplicationRepository, times(1)).save(any(QuestionApplication.class));
    }

    @Test
    void testGetAllQuestionApplicationByApplication() {
        // Arrange
        Long appId = 1L;
        QuestionApplication qa1 = new QuestionApplication();
        qa1.setId(1L);
        when(questionApplicationRepository.findQuestionApplicationById_application(appId)).thenReturn(List.of(qa1));

        // Act
        List<QuestionApplication> result = questionApplicationService.getAllQuestionApplicationByApplication(appId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(questionApplicationRepository, times(1)).findQuestionApplicationById_application(appId);
    }

    @Test
    void testDeleteById() {
        // Arrange
        Long qaId = 1L;

        // Act
        questionApplicationService.deleteById(qaId);

        // Assert
        verify(questionApplicationRepository, times(1)).deleteById(qaId);
    }
}
