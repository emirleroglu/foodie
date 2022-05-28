package com.emirleroglu.foodie.payload.request;

public class IngredientRequest {

    private String name;
    private String username;
    private Long spoonID;

    public IngredientRequest(String name, String username, Long spoonID) {
        this.name = name;
        this.username = username;
        this.spoonID = spoonID;
    }

    public IngredientRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSpoonID() {
        return spoonID;
    }

    public void setSpoonID(Long spoonID) {
        this.spoonID = spoonID;
    }
}
