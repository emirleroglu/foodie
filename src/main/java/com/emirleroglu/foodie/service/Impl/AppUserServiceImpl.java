package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.model.AppUser;
import com.emirleroglu.foodie.payload.request.LoginRequest;
import com.emirleroglu.foodie.payload.request.SignupRequest;
import com.emirleroglu.foodie.payload.response.LoginResponse;
import com.emirleroglu.foodie.payload.response.UserResult;
import com.emirleroglu.foodie.repository.AppUserRepository;
import com.emirleroglu.foodie.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

        AppUser user = new AppUser(request.getUsername(), request.getEmail(), request.getPassword(),false);
        repository.save(user);
        return UserResult.Successful;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<AppUser> myUser = repository.findByUsername(request.getUsername());
        if (!myUser.isPresent()) {
            return new LoginResponse(UserResult.UnknownUser, 0L,false);
        }
        if (Objects.equals(request.getPassword(), myUser.get().getPassword())) {
            return new LoginResponse(UserResult.Successful, myUser.get().getId(),myUser.get().getFirstLogin());
        } else {
            return new LoginResponse(UserResult.WrongPassword, 0L,false);
        }


    }

    @Override
    public List<AppUser> getAppUserLike(String username) {

        return repository.findByUsernameContains(username);
    }
}
