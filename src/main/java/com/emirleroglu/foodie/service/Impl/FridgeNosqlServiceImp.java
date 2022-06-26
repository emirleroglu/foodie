package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.emirleroglu.foodie.service.FridgeNosqlService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FridgeNosqlServiceImp implements FridgeNosqlService {
    public void addData(Firestore db) {


        try {
            DocumentReference docRef = db.collection("users").document("alovelace");

            Map<String, Object> data = new HashMap<>();
            data.put("first", "Emin");
            data.put("last", "Lovelace");
            data.put("born", 1815);

            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addData(Firestore db, List<IngredientRequest> request) {

        DocumentReference docRef = db.collection("users").document(request.get(0).getFridgeID());
        Map<String, Object> data = new HashMap<>();

        for (IngredientRequest e : request) {
            data.put("Ingredient", request);
        }

        ApiFuture<WriteResult> result = docRef.set(data);
    }

    @Override
    public Map<String, Object> getData(Firestore db, String FridgeID) {
        try {
            DocumentReference docRef = db.collection("users").document(FridgeID);

            ApiFuture<DocumentSnapshot> future = docRef.get();

            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.getData();
            } else {
                System.out.println("No such document!");
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return null;
    }
}
