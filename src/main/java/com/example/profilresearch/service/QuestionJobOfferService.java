package com.example.profilresearch.service;

import com.example.profilresearch.entity.JobOffer;
import com.example.profilresearch.entity.Question;
import com.example.profilresearch.entity.QuestionJobOffer;
import com.example.profilresearch.repository.QuestionJobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionJobOfferService {
    private final QuestionJobOfferRepository questionJobOfferRepository;

    public void createQuestionJobOffer(Question question, JobOffer jo, Integer ind) {
        QuestionJobOffer questionJobOffer = new QuestionJobOffer();
        questionJobOffer.setId_question(question);
        questionJobOffer.setId_job_offer(jo);
        questionJobOffer.setQuestion_number(ind);
        questionJobOfferRepository.save(questionJobOffer);
    }
}
