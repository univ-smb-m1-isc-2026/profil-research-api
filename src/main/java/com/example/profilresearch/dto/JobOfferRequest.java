package com.example.profilresearch.dto;

import lombok.Data;

@Data
public class JobOfferRequest {
    private String title;
    private String description;
    private boolean isPublic;

    public boolean getIsPublic(){
        return this.isPublic;
    }
}
