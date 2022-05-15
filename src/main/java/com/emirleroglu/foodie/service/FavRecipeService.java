package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.FavRecipe;

import java.util.List;
import java.util.Optional;

public interface FavRecipeService {
    List<FavRecipe> getFavListByUserId(Long id);

    FavRecipe createFavRecipe(FavRecipe recipe);

    void deleteFavRecipeById(Long id);
}
