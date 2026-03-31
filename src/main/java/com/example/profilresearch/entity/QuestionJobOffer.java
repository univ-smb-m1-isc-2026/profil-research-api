package com.example.profilresearch.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "QuestionJobOffer")
public class QuestionJobOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer question_number; // the number of this question, the appearance order for display

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_question", nullable = false)
    private Question id_question; // the linked question

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jobOffer", nullable = false)
    private JobOffer id_job_offer; // the linked JobOffer
}
