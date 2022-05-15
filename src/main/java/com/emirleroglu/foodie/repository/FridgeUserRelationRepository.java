package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.FridgeUserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FridgeUserRelationRepository extends JpaRepository<FridgeUserRelation, Long> {
    List<FridgeUserRelation> getFridgeUserRelationByAppUserIdAndStatusTrue(Long id);

    List<FridgeUserRelation> getFridgeUserRelationByFridgeId(Long id);

    List<FridgeUserRelation> getFridgeUserRelationByAppUserIdAndStatusFalse(Long id);
}
