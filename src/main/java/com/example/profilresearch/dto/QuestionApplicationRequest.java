package com.example.profilresearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionApplicationRequest {
    private Long id_question;
    private List<String> responses;
}
