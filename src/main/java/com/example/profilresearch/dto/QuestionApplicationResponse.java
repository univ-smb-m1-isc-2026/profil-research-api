package com.example.profilresearch.dto;

import com.example.profilresearch.entity.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionApplicationResponse {
    private Question id_question;
    private List<String> responses;
}
