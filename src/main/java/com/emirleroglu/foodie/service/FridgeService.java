package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.Fridge;
import com.emirleroglu.foodie.model.FridgeUserRelation;

import java.util.List;
import java.util.Optional;

public interface FridgeService {

    Fridge saveFridge(Fridge fridge);

    void createRelation(FridgeUserRelation relation);

    List<FridgeUserRelation> getRelationByAppUserId(Long id);

    List<FridgeUserRelation> getRelationByFridgeId(Long id);

    List<FridgeUserRelation> getRelationByAppUserIdAndStatus(Long id);

    void updateRelationStatus(Long id);

}
