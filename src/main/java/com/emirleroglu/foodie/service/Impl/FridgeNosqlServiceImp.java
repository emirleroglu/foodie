package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.emirleroglu.foodie.service.FridgeNosqlService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
    public void addData(Firestore db, List<IngredientRequest> request) throws ExecutionException, InterruptedException {

        DocumentReference docRef = db.collection("users").document(request.get(0).getFridgeID());
        Map<String, Object> data = new HashMap<>();
        List<IngredientRequest> oldData=new ArrayList<>();

        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        IngredientRequest e=document.toObject(IngredientRequest.class);
       // document.
        //document.get("Ingredient",IngredientRequest.class);

        if(document.exists()){
            Object o= Objects.requireNonNull(document.getData()).get("Ingredient");
            oldData= (List<IngredientRequest>) convertObjectToList(o);
        }
        request.addAll(oldData);
       /* for (IngredientRequest request1:request) {
            data.put(request1.getName(), request);
        }*/
        data.put("Ingredient",request);
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    @Override
    public void addData(Firestore db, IngredientRequest request) throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        DocumentReference docRef = db.collection("users").document(request.getFridgeID());
        data.put(request.getName(),request);
        ApiFuture<WriteResult> result = docRef.update(data);
    }

    @Override
    public Boolean checkAndUpdate(Firestore db, List<IngredientRequest> request) throws ExecutionException, InterruptedException {
        Map<String, Object> data = new HashMap<>();
        DocumentReference docRef = db.collection("users").document(request.get(0).getFridgeID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        int counter=0;
        for (IngredientRequest req: request) {
           IngredientRequest temp= document.get(req.getName(),IngredientRequest.class);

            if (temp != null && temp.getName().equals(req.getName())) {
                double x=temp.getAmount()- req.getAmount();
                if(x<0){
                    return false;
                }
                data.put(req.getName(),new IngredientRequest(req.getName(),req.getFridgeID(),req.getSpoonID(), req.getPossibleUnit(),x));
                counter++;
            }
        }
        if(counter== request.size()) {
            ApiFuture<WriteResult> result = docRef.update(data);
            return true;
        }else {
            return false;
        }
    }


    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
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
