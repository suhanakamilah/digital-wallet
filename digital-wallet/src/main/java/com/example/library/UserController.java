package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    private final UserContainer userContainer;

    public UserController(UserService userService, UserContainer userContainer) {
        this.userService = userService;
        this.userContainer = userContainer;
    }

    //Create user
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users addUser(@RequestBody Users request) {
        return userService.addUser(request);
    }

    //Retrieve user info for transfer
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users getUserInfo(@RequestParam UUID user_id) {
        return userContainer.findUserById(user_id);
    }

    // Retrieve current balance for a user
    @GetMapping(value = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal getUserBalance(@RequestParam UUID user_id) {
        return userContainer.findUserById(user_id).getBalance();
    }
}
