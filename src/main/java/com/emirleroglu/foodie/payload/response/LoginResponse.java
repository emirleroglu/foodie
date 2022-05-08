package com.emirleroglu.foodie.payload.response;

public class LoginResponse {
    UserResult result;
    Long id;

    public LoginResponse(UserResult result, Long id) {
        this.result = result;
        this.id = id;
    }

    public UserResult getResult() {
        return result;
    }

    public void setResult(UserResult result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
