package com.emirleroglu.foodie.model;

import javax.persistence.*;

@Entity
public class Allergen {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    private String name;

    private Long spoonId;
    @OneToOne
    private AppUser user;

    public Allergen() {
    }

    public Allergen(String name, Long spoonId, AppUser user) {
        this.name = name;
        this.spoonId = spoonId;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSpoonId() {
        return spoonId;
    }

    public void setSpoonId(Long spoonId) {
        this.spoonId = spoonId;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
