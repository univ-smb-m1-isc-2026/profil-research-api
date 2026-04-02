package com.example.profilresearch.repository;

import com.example.profilresearch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
