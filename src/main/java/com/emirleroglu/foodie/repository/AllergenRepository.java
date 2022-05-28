package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergenRepository extends JpaRepository<Allergen, Long> {
    List<Allergen> getAllergenByUserId(long userId);
}
