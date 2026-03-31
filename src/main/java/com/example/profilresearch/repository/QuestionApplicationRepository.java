package com.example.profilresearch.repository;

import com.example.profilresearch.entity.QuestionApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionApplicationRepository extends JpaRepository<QuestionApplication, Long> {
    @NativeQuery("SELECT * FROM question_application WHERE id_application = ?1")
    public List<QuestionApplication> findQuestionApplicationById_application(Long id_application);
}
