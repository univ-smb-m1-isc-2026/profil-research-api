package com.example.profilresearch.repository;

import com.example.profilresearch.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findByIsPublicTrue(); // a tester mais normalement en suivant la doc c'est comme ça que ça marche
}
