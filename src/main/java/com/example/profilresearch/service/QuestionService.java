package com.example.profilresearch.service;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.dto.QuestionRequest;
import com.example.profilresearch.entity.*;
import com.example.profilresearch.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public String createQuestion(QuestionRequest request) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        Format format = Format.valueOf(request.getFormat());
        System.out.println(format);
        question.setFormat(format);

        questionRepository.save(question);
        return "Question ajouté";
    }
}
