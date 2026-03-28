package com.example.profilresearch.service;

import com.example.profilresearch.entity.Application;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.entity.QuestionApplication;
import com.example.profilresearch.repository.QuestionApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionApplicationService {
    private final QuestionApplicationRepository questionApplicationRepository;

    public void createQuestionApplication(Question question, Application appli, List<String> responses){
        QuestionApplication qa = new QuestionApplication();
        qa.setId_question(question);
        qa.setId_application(appli);
        qa.setResponses(responses);
        questionApplicationRepository.save(qa);
    }

    public List<QuestionApplication> getAllQuestionApplicationByApplication(Long applicationId){
        return questionApplicationRepository.findQuestionApplicationById_application(applicationId);
    }

    public void deleteById(Long qaId) {
        questionApplicationRepository.deleteById(qaId);
    }
}
