package com.example.profilresearch.repository;

import com.example.profilresearch.entity.ChoiceFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceFormatRepository extends JpaRepository<ChoiceFormat, Long> {
}
