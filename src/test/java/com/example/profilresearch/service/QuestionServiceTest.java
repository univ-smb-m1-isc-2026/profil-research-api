package com.example.profilresearch.service;

import com.example.profilresearch.dto.QuestionRequest;
import com.example.profilresearch.entity.Format;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void testGetQuestionById() {
        // Arrange
        Long id = 1L;
        Question question = new Question();
        question.setId(id);
        when(questionRepository.findById(id)).thenReturn(Optional.of(question));

        // Act
        Optional<Question> result = questionService.getQuestionById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(questionRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllQuestion() {
        // Arrange
        Question q1 = new Question();
        q1.setTitle("Question 1");
        Question q2 = new Question();
        q2.setTitle("Question 2");
        when(questionRepository.findAll()).thenReturn(Arrays.asList(q1, q2));

        // Act
        List<Question> result = questionService.getAllQuestion();

        // Assert
        assertEquals(2, result.size());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void testCreateQuestion() {
        // Arrange
        QuestionRequest request = new QuestionRequest();
        request.setTitle("Nouvelle Question");
        request.setFormat("RADIO");
        request.setChoices(Arrays.asList("Oui", "Non"));

        Question savedQuestion = new Question();
        savedQuestion.setId(1L);
        savedQuestion.setTitle(request.getTitle());
        savedQuestion.setFormat(Format.RADIO);
        savedQuestion.setChoices(request.getChoices());

        when(questionRepository.save(any(Question.class))).thenReturn(savedQuestion);

        // Act
        Question result = questionService.createQuestion(request);

        // Assert
        assertNotNull(result);
        assertEquals("Nouvelle Question", result.getTitle());
        assertEquals(Format.RADIO, result.getFormat());
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void testCreateQuestionInvalidFormat() {
        // Arrange
        QuestionRequest request = new QuestionRequest();
        request.setFormat("INVALID_FORMAT");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> questionService.createQuestion(request));
    }
}
