package com.emirleroglu.foodie.service.Impl;

import com.emirleroglu.foodie.model.AppUser;
import com.emirleroglu.foodie.model.Preferences;
import com.emirleroglu.foodie.repository.AllergenRepository;
import com.emirleroglu.foodie.repository.AppUserRepository;
import com.emirleroglu.foodie.repository.PreferencesRepository;
import com.emirleroglu.foodie.service.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferencesServiceImpl implements PreferencesService {
    PreferencesRepository repository;
    AppUserRepository userRepository;

    @Autowired
    public PreferencesServiceImpl(PreferencesRepository repository, AppUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Preferences getPreferencesByUserId(Long Id) {
        Optional<Preferences> myPreference = repository.getPreferencesByAppUserId(Id);

        return myPreference.orElseThrow();
    }

    @Override
    public Preferences createPreferences(Preferences preferences) {
        AppUser user = userRepository.getById(preferences.getAppUser().getId());
        user.setFirstLogin(true);
        userRepository.save(user);
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
