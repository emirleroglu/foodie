package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.google.cloud.firestore.Firestore;

import java.util.List;
import java.util.Map;

public interface FridgeNosqlService {

    void addData(Firestore db, List<IngredientRequest> request);

    Map<String, Object> getData(Firestore db, String username);
}
