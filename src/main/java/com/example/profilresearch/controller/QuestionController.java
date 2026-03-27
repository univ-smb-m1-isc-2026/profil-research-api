package com.example.profilresearch.controller;

import com.example.profilresearch.dto.QuestionRequest;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question") // start of all requests for this file
@RequiredArgsConstructor
public class QuestionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionService questionService;

    @GetMapping("/getAll")
    public List<Question> getAllQuestion() {
        logger.info("Fetching all questions");
        return questionService.getAllQuestion();
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestBody QuestionRequest request) {
        logger.info("Adding new question: {}", request.getTitle());
        return questionService.createQuestion(request);
    }
}
