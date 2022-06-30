package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.google.cloud.firestore.Firestore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface FridgeNosqlService {

    void addData(Firestore db, List<IngredientRequest> request) throws ExecutionException, InterruptedException;

    Map<String, Object> getData(Firestore db, String username);
    public void addData(Firestore db);
}
