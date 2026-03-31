package com.example.profilresearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationRequest {
    private String mail;
    private String lastname;
    private String firstname;
    private Long jobOffer_id;
    private List<QuestionApplicationRequest> responses;
}
