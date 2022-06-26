package com.emirleroglu.foodie.payload.request;

public class IngredientRequest {

    private String name;
    private String fridgeID;
    private Long spoonID;
    private String possibleUnit;
    private Double amount;

    public IngredientRequest(String name, String fridgeID, Long spoonID, String possibleUnit, Double amount) {
        this.name = name;
        this.fridgeID = fridgeID;
        this.spoonID = spoonID;
        this.possibleUnit = possibleUnit;
        this.amount = amount;
    }

    public String getFridgeID() {
        return fridgeID;
    }

    public void setFridgeID(String fridgeID) {
        this.fridgeID = fridgeID;
    }

    public String getPossibleUnit() {
        return possibleUnit;
    }

    public void setPossibleUnit(String possibleUnit) {
        this.possibleUnit = possibleUnit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public IngredientRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getSpoonID() {
        return spoonID;
    }

    public void setSpoonID(Long spoonID) {
        this.spoonID = spoonID;
    }
}
