package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.AppUser;
import com.emirleroglu.foodie.payload.request.LoginRequest;
import com.emirleroglu.foodie.payload.response.LoginResponse;
import com.emirleroglu.foodie.payload.response.UserResult;
import com.emirleroglu.foodie.payload.request.SignupRequest;

import java.util.List;

public interface AppUserService {
    UserResult register(SignupRequest request);

    LoginResponse login(LoginRequest request);

    List<AppUser> getAppUserLike(String username);
}
