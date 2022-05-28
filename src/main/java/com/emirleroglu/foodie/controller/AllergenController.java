package com.emirleroglu.foodie.controller;

import com.emirleroglu.foodie.model.Allergen;
import com.emirleroglu.foodie.model.Preferences;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.service.AllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allergen")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AllergenController {
    AllergenService service;

    @Autowired
    public AllergenController(AllergenService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<?> createPreferences(@RequestBody List<Allergen> allergenList) {
        List<Allergen> allergen = service.saveAllergen(allergenList);
        if (allergen == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hatalı allergen"));
        }
        return ResponseEntity.ok(allergen);
    }

    @GetMapping("/getAllergenByUserId")
    public ResponseEntity<?> getAllergenListByUserId(@RequestParam Long id) {
        List<Allergen> allergenList = service.getAllergenListByUserID(id);


        return ResponseEntity.ok(allergenList);
    }
}
