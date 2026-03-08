package com.example.profilresearch.controller;

import com.example.profilresearch.entity.Question;
import com.example.profilresearch.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/question") // start of all requests for this file
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }
}
