package com.example.profilresearch.repository;

import com.example.profilresearch.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByJobOffer_IdJobOffer(Long idJobOffer); // bizzare l'erreur, a verifier
}
