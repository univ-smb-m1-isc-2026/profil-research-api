package com.example.profilresearch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mail;
    private String lastname;
    private String firstname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jobOffer", nullable = false)
    private JobOffer jobOffer; // the joboffer linked to the application
}
