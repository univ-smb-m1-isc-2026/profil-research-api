package com.example.profilresearch.repository;

import com.example.profilresearch.entity.QuestionJobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJobOfferRepository extends JpaRepository<QuestionJobOffer, Long> {
    @NativeQuery("SELECT * FROM question_job_offer WHERE id_job_offer = ?1 ORDER BY question_number")
    List<QuestionJobOffer> findQuestionJobOfferById_job_offer(Long id_job_offer);
}
