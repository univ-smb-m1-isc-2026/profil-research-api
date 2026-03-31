package com.example.profilresearch.repository;

import com.example.profilresearch.entity.Application;
import com.example.profilresearch.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByJobOffer_Id(Long id);
}
