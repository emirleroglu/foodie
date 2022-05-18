package com.emirleroglu.foodie.controller;

import com.emirleroglu.foodie.model.FavRecipe;
import com.emirleroglu.foodie.payload.response.MessageResponse;
import com.emirleroglu.foodie.service.FavRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fav")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FavRecipeController {
    FavRecipeService service;

    @Autowired
    public FavRecipeController(FavRecipeService service) {
        this.service = service;
    }

    @PostMapping("/create")
    ResponseEntity<?> createFavRecipe(@RequestBody FavRecipe recipe) {
        FavRecipe favRecipe = service.createFavRecipe(recipe);
        if (favRecipe.getSpoonApiId() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Aynı SpoonID var.Değiştirin "));
        }
        return ResponseEntity
                .ok(favRecipe);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteFavRecipeById(@RequestParam Long id) {
        service.deleteFavRecipeById(id);
        return ResponseEntity
                .ok(new MessageResponse("Başarılı silme  işlemi."));
    }

    @GetMapping("/favList")
    ResponseEntity<?> getFavListUserId(@RequestParam Long id) {
        List<FavRecipe> favRecipes = service.getFavListByUserId(id);
        if (favRecipes.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bu AppUser Id ile favList yok."));
        } else {
            return ResponseEntity
                    .ok(favRecipes);
        }
    }
}
