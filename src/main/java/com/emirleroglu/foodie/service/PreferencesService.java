package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.Preferences;

import java.util.Optional;

public interface PreferencesService {
    Preferences getPreferencesByUserId(Long Id);

    Preferences createPreferences(Preferences preferences);

    Optional<Preferences> updatePreference(Preferences preference, Long id);


}
