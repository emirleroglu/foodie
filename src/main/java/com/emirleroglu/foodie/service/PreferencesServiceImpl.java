package com.emirleroglu.foodie.service;

import com.emirleroglu.foodie.model.Preferences;
import com.emirleroglu.foodie.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferencesServiceImpl implements PreferencesService {
    PreferencesRepository repository;

    @Autowired
    public PreferencesServiceImpl(PreferencesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Preferences getPreferencesByUserId(Long Id) {
        Optional<Preferences> myPreference = repository.getPreferencesByAppUserId(Id);

        return myPreference.orElseThrow();
    }

    @Override
    public Preferences createPreferences(Preferences preferences) {
        // Patlayabiliyor.
        if (repository.existsByAppUserId(preferences.getAppUser().getId())) {
            return null;
        }

        repository.save(preferences);

        return preferences;

    }

    @Override
    public Optional<Preferences> updatePreference(Preferences preference, Long id) {
        Optional<Preferences> myPreference = repository.getPreferencesByAppUserId(id);
        if (myPreference.isPresent()) {
            myPreference.get().setVegetarian(preference.getVegetarian());
            myPreference.get().setVegan(preference.getVegan());
            myPreference.get().setDairyFree(preference.getDairyFree());
            myPreference.get().setGlutenFree(preference.getGlutenFree());
            myPreference.get().setVeryHealthy(preference.getVeryHealthy());
            repository.save(myPreference.get());

            return myPreference;

        }

        return Optional.empty();
    }
}
