package com.example.profilresearch.repository;

import com.example.profilresearch.entity.QuestionJobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJobOfferRepository extends JpaRepository<QuestionJobOffer, Long> {
    @Query("select q from QuestionJobOffer q where q.id_job_offer.id = ?1 order by q.question_number")
    List<QuestionJobOffer> findQuestionJobOfferById_job_offer(Long id_job_offer);
}
