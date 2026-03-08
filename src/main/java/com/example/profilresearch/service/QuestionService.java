package com.example.profilresearch.service;

import com.example.profilresearch.entity.Question;
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
}
