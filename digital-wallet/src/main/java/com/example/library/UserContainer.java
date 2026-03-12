package com.example.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserContainer extends JpaRepository<Users, UUID> {
    Users findUserById(UUID id);
    Users findByEmail(String email);
}