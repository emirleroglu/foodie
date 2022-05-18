package com.emirleroglu.foodie.controller;

import com.emirleroglu.foodie.model.Preferences;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PreferencesController {
    PreferencesService service;

    @Autowired
    public PreferencesController(PreferencesService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPreferences(@RequestBody Preferences preferences) {
        Preferences myPreferences = service.createPreferences(preferences);
        if (myPreferences == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Aynı kullanıcıya birden fazla preference eklemeye çalışıyorsun."));
        }
        return ResponseEntity.ok(myPreferences);
    }

    @GetMapping("/getPreferenceById")
    public ResponseEntity<?> getPreferenceById(@RequestParam Long id) {
        Preferences myPreference = service.getPreferencesByUserId(id);


        return ResponseEntity.ok(myPreference);
    }

    @PutMapping("/update")
    ResponseEntity<?> updatePreferences(@RequestBody Preferences preferences, @RequestParam Long id) {
        Optional<Preferences> myPreferences = service.updatePreference(preferences, id);
        if (myPreferences.isPresent()) {
            return ResponseEntity.ok(myPreferences);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Yanlış UserId"));
    }


}
