package com.emirleroglu.foodie.model;

import javax.persistence.*;

@Entity
public class Preferences {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean vegetarian;

    private Boolean vegan;

    private Boolean glutenFree;

    private Boolean dairyFree;

    private Boolean veryHealthy;

    @OneToOne(fetch = FetchType.EAGER)
    private AppUser appUser;

    public Preferences() {
    }

    public Preferences(Boolean vegetarian, Boolean vegan, Boolean glutenFree, Boolean dairyFree, Boolean veryHealthy, AppUser user) {

        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.appUser = user;

    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public Boolean getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(Boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public Boolean getVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(Boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
