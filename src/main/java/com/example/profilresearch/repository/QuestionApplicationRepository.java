package com.example.profilresearch.repository;

import com.example.profilresearch.entity.QuestionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionApplicationRepository extends JpaRepository<QuestionApplication, Long> {
}
