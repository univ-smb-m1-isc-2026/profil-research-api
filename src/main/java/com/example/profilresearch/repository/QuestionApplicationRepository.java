package com.example.profilresearch.repository;

import com.example.profilresearch.entity.QuestionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionApplicationRepository extends JpaRepository<QuestionApplication, Long> {
    @Query("select q FROM QuestionApplication q where q.id_application.id = ?1")
    List<QuestionApplication> findQuestionApplicationById_application(Long id_application);
}
