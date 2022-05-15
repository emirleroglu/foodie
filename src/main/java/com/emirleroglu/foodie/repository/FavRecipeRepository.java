package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.FavRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavRecipeRepository extends JpaRepository<FavRecipe, Long> {
    List<FavRecipe> getFavRecipeRepositoryByAppUserId(Long id);
}
