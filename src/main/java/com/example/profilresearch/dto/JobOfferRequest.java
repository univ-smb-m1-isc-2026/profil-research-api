package com.example.profilresearch.dto;

import lombok.Data;

@Data
public class JobOfferRequest {
    private String title;
    private String description;
    private boolean isPublic;
    private String contractType;
    private String location;

    public boolean getIsPublic(){
        return this.isPublic;
    }
}
