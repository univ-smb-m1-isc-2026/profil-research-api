package com.example.profilresearch.dto;

import lombok.Data;

@Data
public class ApplicationRequest {
    private String mail;
    private String lastname;
    private String firstname;
    private Long jobOffer_id;
}
