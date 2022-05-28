package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.model.Allergen;
import com.emirleroglu.foodie.repository.AllergenRepository;
import com.emirleroglu.foodie.service.AllergenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergenServiceImpl implements AllergenService {

    AllergenRepository repository;

    @Autowired
    public AllergenServiceImpl(AllergenRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Allergen> saveAllergen(List<Allergen> allergens) {
        return repository.saveAll(allergens);
    }

    @Override
    public List<Allergen> getAllergenListByUserID(Long id) {
        return repository.getAllergenByUserId(id);
    }
}
