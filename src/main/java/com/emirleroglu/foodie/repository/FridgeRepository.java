package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge,Long> {
}
