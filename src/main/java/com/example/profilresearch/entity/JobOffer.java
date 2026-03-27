package com.example.profilresearch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "JobOffer")
public class JobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean isPublic;
    private String contractType;
    private String location;
    private String date;

    public void setIsPublic(boolean ip){
        this.isPublic = ip;
    }
}
