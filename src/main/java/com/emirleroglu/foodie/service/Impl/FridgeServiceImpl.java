package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.model.Fridge;
import com.emirleroglu.foodie.model.FridgeUserRelation;
import com.emirleroglu.foodie.repository.FridgeRepository;
import com.emirleroglu.foodie.repository.FridgeUserRelationRepository;
import com.emirleroglu.foodie.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FridgeServiceImpl implements FridgeService {
    private final FridgeRepository fridgeRepository;
    private final FridgeUserRelationRepository fridgeUserRelationRepository;

    @Autowired
    public FridgeServiceImpl(FridgeRepository fridgeRepository, FridgeUserRelationRepository fridgeUserRelationRepository) {
        this.fridgeRepository = fridgeRepository;
        this.fridgeUserRelationRepository = fridgeUserRelationRepository;
    }


    @Override
    public Fridge saveFridge(Fridge fridge) {
        fridgeRepository.save(fridge);
        return fridge;
    }


    @Override
    public void createRelation(FridgeUserRelation relation) {
        fridgeUserRelationRepository.save(relation);
    }

    @Override
    public List<FridgeUserRelation> getRelationByAppUserId(Long id) {
        return fridgeUserRelationRepository.getFridgeUserRelationByAppUserIdAndStatusTrue(id);
    }

    @Override
    public List<FridgeUserRelation> getRelationByFridgeId(Long id) {
        return fridgeUserRelationRepository.getFridgeUserRelationByFridgeId(id);
    }

    @Override
    public List<FridgeUserRelation> getRelationByAppUserIdAndStatus(Long id) {
        return fridgeUserRelationRepository.getFridgeUserRelationByAppUserIdAndStatusFalse(id);
    }

    @Override
    public void updateRelationStatus(Long id) {
        FridgeUserRelation relation = fridgeUserRelationRepository.getById(id);

        relation.setStatus(true);
        fridgeUserRelationRepository.save(relation);


    }
}
