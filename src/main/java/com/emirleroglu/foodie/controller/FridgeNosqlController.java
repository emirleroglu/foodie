package com.emirleroglu.foodie.controller;


import com.emirleroglu.foodie.model.Ingredient;
import com.emirleroglu.foodie.payload.request.IngredientRequest;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.service.FridgeNosqlService;
import com.emirleroglu.foodie.service.Impl.FridgeNosqlServiceImp;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/nosql")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FridgeNosqlController {

    FridgeNosqlService serviceImp;
    Firestore db;

    @Autowired
    public FridgeNosqlController(FridgeNosqlServiceImp serviceImp, Firestore db) {
        this.serviceImp = serviceImp;
        this.db = db;
    }

    @PostMapping("/addList")
    ResponseEntity<?> addListByUserId(@RequestBody List<IngredientRequest> request) throws ExecutionException, InterruptedException, IOException {
        serviceImp.addData(db,request);
        return ResponseEntity.ok(new MessageResponse("oldu galiba"));
    }

    @GetMapping("/getList")
    ResponseEntity<?> getListByUsername(@RequestParam String username) {
        Map<String, Object> data = serviceImp.getData(db, username);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/checkAndUpdateList")
    ResponseEntity<?> checkAndUpdateListByUserId(@RequestBody List<IngredientRequest> request) throws ExecutionException, InterruptedException, IOException {
        boolean resp= serviceImp.checkAndUpdate(db,request);
        if(resp) {
            return ResponseEntity.ok(new MessageResponse("Dolaptan malzemeler azaldı."));
        }else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Dolapta Yeterli malzeme yok."));
        }

    }
    @PostMapping("/addListElement")
    ResponseEntity<?> addListByUserId(@RequestBody IngredientRequest request) throws ExecutionException, InterruptedException, IOException {
        serviceImp.addData(db,request);
        return ResponseEntity.ok(new MessageResponse("oldu galiba"));
    }



}
