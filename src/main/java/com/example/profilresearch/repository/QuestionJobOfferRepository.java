package com.example.profilresearch.repository;

import com.example.profilresearch.entity.QuestionJobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionJobOfferRepository extends JpaRepository<QuestionJobOffer, Long> {
}
