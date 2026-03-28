package com.example.profilresearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationResponse {
    private Long id;
    private String mail;
    private String lastname;
    private String firstname;
    private Long id_job_offer;
    private List<QuestionApplicationResponse> responses;
}
