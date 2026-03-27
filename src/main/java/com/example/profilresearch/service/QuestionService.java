package com.example.profilresearch.service;

import com.example.profilresearch.dto.QuestionRequest;
import com.example.profilresearch.entity.*;
import com.example.profilresearch.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public String createQuestion(QuestionRequest request) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        Format format = Format.valueOf(request.getFormat());
        logger.debug("Format detected: {}", format);
        question.setFormat(format);

        questionRepository.save(question);
        logger.info("Question created: {}", question.getTitle());
        return "Question ajouté";
    }
}
