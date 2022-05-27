package com.emirleroglu.foodie.payload.response;

public class LoginResponse {
    UserResult result;
    Long id;
    Boolean firstLogin;

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public LoginResponse(UserResult result, Long id, Boolean firstLogin) {
        this.result = result;
        this.id = id;
        this.firstLogin = firstLogin;
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
