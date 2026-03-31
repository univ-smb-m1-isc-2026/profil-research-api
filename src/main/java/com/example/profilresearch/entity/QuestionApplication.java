package com.example.profilresearch.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "QuestionApplication")
public class QuestionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private List<String> responses; // the response of the user

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    private Question id_question; // the linked question

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_application", nullable = false)
    private Application id_application; // the linked application
}
