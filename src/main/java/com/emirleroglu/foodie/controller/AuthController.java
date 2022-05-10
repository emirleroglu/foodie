package com.emirleroglu.foodie.controller;

import com.emirleroglu.foodie.model.AppUser;
import com.emirleroglu.foodie.payload.request.LoginRequest;
import com.emirleroglu.foodie.payload.request.SignupRequest;
import com.emirleroglu.foodie.payload.response.LoginResponse;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.payload.response.UserResult;
import com.emirleroglu.foodie.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest request) {
        LoginResponse result = service.login(request);
        if (result.getResult() == UserResult.WrongPassword) {
            return ResponseEntity
                    .badRequest()
                    .body(new LoginResponse(result.getResult(), result.getId()));
        }
        if (result.getResult() == UserResult.UnknownUser) {
            return ResponseEntity
                    .badRequest()
                    .body(new LoginResponse(result.getResult(), result.getId()));
        }

        return ResponseEntity.ok(new LoginResponse(result.getResult(), result.getId()));
    }

    @GetMapping("/contains")
    public ResponseEntity<?> getAppUserLike(@RequestParam String username) {
        List<AppUser> userList = service.getAppUserLike(username);

        return ResponseEntity.ok(userList);
    }
}
