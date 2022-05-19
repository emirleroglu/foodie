package com.emirleroglu.foodie.service;

import com.google.cloud.firestore.Firestore;

public interface FridgeNosqlService {

    void addData(Firestore db);
}
