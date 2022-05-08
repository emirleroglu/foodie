package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.payload.request.LoginRequest;
import com.emirleroglu.foodie.payload.response.UserResult;
import com.emirleroglu.foodie.payload.request.SignupRequest;

public interface AppUserService {
    UserResult register(SignupRequest request);

    UserResult login(LoginRequest request);
}
