package com.example.profilresearch.dto;

import com.example.profilresearch.entity.Question;
import lombok.Data;

@Data
public class QuestionJobOfferResponse {
    private Long id;
    private Integer question_number;
    private Question id_question;
    private Long id_job_offer;
}
