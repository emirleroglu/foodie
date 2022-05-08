package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.AppUser;
import com.emirleroglu.foodie.payload.request.LoginRequest;
import com.emirleroglu.foodie.payload.request.SignupRequest;
import com.emirleroglu.foodie.payload.response.UserResult;
import com.emirleroglu.foodie.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserRepository repository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResult register(SignupRequest request) {
        if (repository.existsByUsername(request.getUsername())) {
            return UserResult.takenUsername;
        }

        if (repository.existsByEmail(request.getEmail())) {
            return UserResult.takenEmail;
        }

        AppUser user = new AppUser(request.getUsername(), request.getEmail(), request.getPassword());
        repository.save(user);
        return UserResult.Successful;
    }

    @Override
    public UserResult login(LoginRequest request) {
        return null;
    }
}
