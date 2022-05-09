package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
    Optional<Preferences> getPreferencesByAppUserId(Long Id);

    Boolean existsByAppUserId(Long Id);
}
