package com.emirleroglu.foodie.repository;

import com.emirleroglu.foodie.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);


    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<AppUser> findByUsernameContains(String username);
}
