package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
