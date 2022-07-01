package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.google.cloud.firestore.Firestore;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface FridgeNosqlService {

    void addData(Firestore db, List<IngredientRequest> request) throws ExecutionException, InterruptedException;
    void addData(Firestore db, IngredientRequest request) throws ExecutionException, InterruptedException, IOException;
    Boolean checkAndUpdate(Firestore db, List<IngredientRequest> request) throws ExecutionException, InterruptedException, IOException;
    Map<String, Object> getData(Firestore db, String username);
    public void addData(Firestore db);
}
