package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.Allergen;

import java.util.List;

public interface AllergenService {
    List<Allergen> saveAllergen(List<Allergen> allergens);

    List<Allergen> getAllergenListByUserID(Long id);
}
