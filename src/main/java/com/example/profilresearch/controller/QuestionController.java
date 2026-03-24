package com.example.profilresearch.controller;

import com.example.profilresearch.dto.ApplicationRequest;
import com.example.profilresearch.dto.QuestionRequest;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question") // start of all requests for this file
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/getAll")
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody QuestionRequest request) {
        return questionService.createQuestion(request);
    }
}
