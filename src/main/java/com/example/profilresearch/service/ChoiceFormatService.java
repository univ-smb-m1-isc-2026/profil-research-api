package com.example.profilresearch.service;

import com.example.profilresearch.entity.ChoiceFormat;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.repository.ChoiceFormatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChoiceFormatService {
    private final ChoiceFormatRepository choiceFormatRepository;

    public void createChoiceFormat(String choice, Question question) {
        ChoiceFormat choiceFormat = new ChoiceFormat();
        choiceFormat.setChoice(choice);
        choiceFormat.setQuestion(question);
        choiceFormatRepository.save(choiceFormat);
    }
}
