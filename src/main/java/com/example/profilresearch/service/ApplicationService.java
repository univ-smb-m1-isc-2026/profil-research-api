package com.example.profilresearch.service;

import com.example.profilresearch.entity.Application;
import com.example.profilresearch.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public List<Application> getApplicationByJobOffer(String id) {
        Long jobOfferId = Long.parseLong(id);
        return applicationRepository.findAllByJobOffer_IdJobOffer(jobOfferId);
    }

    public void deleteById(String id) {
        Long applicationId = Long.parseLong(id);
        applicationRepository.deleteById(applicationId);
    }
}
