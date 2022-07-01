package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.emirleroglu.foodie.payload.response.ConversionResponse;
import com.emirleroglu.foodie.service.FridgeNosqlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mashape.unirest.http.Unirest;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        for (IngredientRequest request1:request) {
            data.put(request1.getName(), request);
        }
        data.put("Ingredient",request);
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    @Override
    public void addData(Firestore db, IngredientRequest request) throws ExecutionException, InterruptedException, IOException {
        Map<String, Object> data = new HashMap<>();
        DocumentReference docRef = db.collection("users").document(request.getFridgeID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        IngredientRequest temp= document.get(request.getName(),IngredientRequest.class);
        ConversionResponse conversion;
        if (temp != null) {
            conversion = getResponse(request.getName(), temp.getPossibleUnit(), request.getPossibleUnit(), request.getAmount());
            if(Objects.equals(temp.getName(), request.getName())) {
                double diff=temp.getAmount() + conversion.getTargetAmount();
                request.setAmount(diff);
                request.setPossibleUnit(conversion.getTargetUnit());
            }
        }
        data.put(request.getName(),request);
        ApiFuture<WriteResult> result = docRef.set(data,SetOptions.merge());
    }

    @Override
    public Boolean checkAndUpdate(Firestore db, List<IngredientRequest> request) throws ExecutionException, InterruptedException, IOException {
        Map<String, Object> data = new HashMap<>();
        DocumentReference docRef = db.collection("users").document(request.get(0).getFridgeID());
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        int counter=0;
        for (IngredientRequest req: request) {
           IngredientRequest temp= document.get(req.getName(),IngredientRequest.class);
            if(temp==null){
                continue;
            }
            ConversionResponse conversion=getResponse(req.getName(), temp.getPossibleUnit(), req.getPossibleUnit(), req.getAmount());
            if (temp.getName().equals(req.getName())) {
                double diff=temp.getAmount()- conversion.getTargetAmount();
                if(diff<0){
                    return false;
                }
                data.put(req.getName(),new IngredientRequest(req.getName(),req.getFridgeID(),req.getSpoonID(), conversion.getTargetUnit(), diff));
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

    public ConversionResponse getResponse(String name,String targetUnit,String sourceUnit,double amount) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request e = new Request.Builder()
                .url("https://api.spoonacular.com/recipes/convert?apiKey=451e4340a6274c60ad61d133ef6798a0&ingredientName="+name+"&sourceAmount="+amount+"&targetUnit="+targetUnit+"&sourceUnit="+sourceUnit)
                .get()
                .build();


        ObjectMapper mapper = new ObjectMapper();
        ConversionResponse conversion=new ConversionResponse();
        try (Response response = client.newCall(e).execute()) {
            conversion = mapper.readValue(response.body().string(), ConversionResponse.class);
        }
        return conversion;

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
