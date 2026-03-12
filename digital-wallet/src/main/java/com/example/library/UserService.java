package com.example.library;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserContainer userContainer;

    public UserService(UserContainer userContainer) {
        this.userContainer = userContainer;
    }


    public Users addUser(Users request) {
        // check if mandatory field is empty
        if (request.getName() == null || request.getName().trim().isEmpty())
            throw new IllegalArgumentException("Name is required");

        if (request.getEmail() == null || request.getEmail().trim().isEmpty())
            throw new IllegalArgumentException("Email is required");

        // check if email already exists
        Users existing = userContainer.findByEmail(request.getEmail());
        if (existing != null) {
            throw new IllegalArgumentException("Email already exists.");
        }
        request.setBalance(BigDecimal.valueOf(0));
        return userContainer.save(request);
    }
}
