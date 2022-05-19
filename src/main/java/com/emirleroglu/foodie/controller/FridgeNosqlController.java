package com.emirleroglu.foodie.controller;


import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.service.FridgeNosqlService;
import com.emirleroglu.foodie.service.Impl.FridgeNosqlServiceImp;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @GetMapping("getDeneme")
    ResponseEntity<?> getDeneme() throws ExecutionException, InterruptedException, IOException {
        serviceImp.addData(db);
        return ResponseEntity.ok(new MessageResponse("oldu galiba"));
    }
}
