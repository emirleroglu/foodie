package com.emirleroglu.foodie.controller;

import com.emirleroglu.foodie.payload.request.SignupRequest;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.payload.response.UserResult;
import com.emirleroglu.foodie.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AppUserService service;

    @Autowired
    public AuthController(AppUserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        UserResult result = service.register(signupRequest);
        if (result == UserResult.takenUsername) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (result == UserResult.takenEmail) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}