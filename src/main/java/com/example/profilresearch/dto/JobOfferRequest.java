package com.example.profilresearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class JobOfferRequest {
    private String title;
    private String description;
    private boolean isPublic;
    private String contractType;
    private String location;
    private List<Long> id_question;

    public boolean getIsPublic(){
        return this.isPublic;
    }
}
