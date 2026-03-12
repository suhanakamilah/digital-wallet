package com.example.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionContainer extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySourceUser(UUID sourceUser);
}