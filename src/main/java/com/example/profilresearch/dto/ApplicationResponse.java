package com.example.profilresearch.dto;

import lombok.Data;

@Data
public class ApplicationResponse {
    private Long id;
    private String mail;
    private String lastname;
    private String firstname;
    private Long id_job_offer;
}
