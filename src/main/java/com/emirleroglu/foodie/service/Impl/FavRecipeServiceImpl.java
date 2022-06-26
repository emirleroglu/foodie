package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.model.FavRecipe;
import com.emirleroglu.foodie.repository.FavRecipeRepository;
import com.emirleroglu.foodie.service.FavRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavRecipeServiceImpl implements FavRecipeService {
    FavRecipeRepository repository;

    @Autowired
    public FavRecipeServiceImpl(FavRecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FavRecipe> getFavListByUserId(Long id) {

        return repository.getFavRecipeRepositoryByAppUserId(id);
    }

    @Override
    public FavRecipe createFavRecipe(FavRecipe recipe) {
        //  FavRecipe myRecipe = new FavRecipe(recipe.getTitle(), recipe.getSpoonApiId(), recipe.getAppUser(), recipe.getImageURL());
        repository.save(recipe);
        return recipe;
    }

    @Override
    public void deleteFavRecipeById(Long id) {
        repository.deleteById(id);

    }
}
