package com.example.library;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
public class Users {


    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email; //unique
    private BigDecimal balance;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {this.id =id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
