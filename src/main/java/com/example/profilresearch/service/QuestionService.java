package com.example.profilresearch.service;

import com.example.profilresearch.dto.QuestionRequest;
import com.example.profilresearch.entity.*;
import com.example.profilresearch.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final QuestionRepository questionRepository;
    private final ChoiceFormatService choiceFormatService;

    public Optional<Question> getQuestionById(Long id){
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public String createQuestion(QuestionRequest request) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        Format format = Format.valueOf(request.getFormat());
        logger.debug("Format detected: {}", format);
        question.setFormat(format);

        logger.info("Question created: {}", question.getTitle());
        Question quest = questionRepository.save(question);

        if(format != Format.TEXT){
            // ajouter les choice format
            for (int i = 0; i<request.getChoices().size(); i++){
                choiceFormatService.createChoiceFormat(request.getChoices().get(i), quest);
            }
        }
        return "Question ajouté";
    }
}
