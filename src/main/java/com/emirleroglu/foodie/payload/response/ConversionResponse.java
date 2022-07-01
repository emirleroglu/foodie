package com.emirleroglu.foodie.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversionResponse {
    @JsonProperty("sourceAmount")
     double sourceAmount;
    @JsonProperty("sourceUnit")
    String sourceUnit;
    @JsonProperty("targetAmount")
    double targetAmount;
    @JsonProperty("targetUnit")
    String targetUnit;
    @JsonProperty("answer")
    String answer;
    @JsonProperty("type")
    String type;

    public ConversionResponse() {
    }

    public ConversionResponse(double sourceAmount, String sourceUnit, double targetAmount, String targetUnit, String answer, String type) {
        this.sourceAmount = sourceAmount;
        this.sourceUnit = sourceUnit;
        this.targetAmount = targetAmount;
        this.targetUnit = targetUnit;
        this.answer = answer;
        this.type = type;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
