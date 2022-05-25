package com.emirleroglu.foodie.controller;

import com.emirleroglu.foodie.model.Fridge;
import com.emirleroglu.foodie.model.FridgeUserRelation;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fridge")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FridgeController {

    FridgeService service;

    @Autowired
    public FridgeController(FridgeService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFridge(@RequestBody Fridge fridge) {

        service.saveFridge(fridge);
        return ResponseEntity
                .ok(fridge);
    }

    @PostMapping("/createRelation")
    public ResponseEntity<?> createFridgeUserRelation(@RequestBody FridgeUserRelation relation) {
        service.createRelation(relation);
        return ResponseEntity
                .ok(relation);
    }

    @GetMapping("/getRelationByUserId")
    public ResponseEntity<?> getRelationByUserId(@RequestParam Long id) {
        List<FridgeUserRelation> relation = service.getRelationByAppUserId(id);
        if (relation.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hatalı UserID"));

        } else {
            return ResponseEntity
                    .ok(relation);

        }
    }

    @GetMapping("/getRelationByFridgeId")
    public ResponseEntity<?> getRelationByFridgeId(@RequestParam Long id) {
        List<FridgeUserRelation> relation = service.getRelationByFridgeId(id);
        if (relation.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hatalı FridgeID"));

        } else {
            return ResponseEntity
                    .ok(relation);

        }
    }

    @GetMapping("/getRelationFridgeRequestByStatus")
    public ResponseEntity<?> getRelationFridgeRequestByStatus(@RequestParam Long id) {
        List<FridgeUserRelation> relations = service.getRelationByAppUserIdAndStatus(id);
        if (relations.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hatalı FridgeID yada status"));
        } else {
            return ResponseEntity
                    .ok(relations);
        }
    }

    @PutMapping ("/acceptFridge")
    public ResponseEntity<?> acceptFridgeByRelationId(@RequestParam Long relationId) {
        service.updateRelationStatus(relationId);
        return ResponseEntity
                .ok(new MessageResponse("Kabul edildi."));
    }

    @PostMapping("/declineFridge")
    public ResponseEntity<?> declineFridgeRelation(@RequestParam Long relationId) {
        service.deleteRelation(relationId);
        return ResponseEntity
                .ok(new MessageResponse("Silindi."));
    }
}
