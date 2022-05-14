package com.emirleroglu.foodie.model;

import javax.persistence.*;

@Entity
public class FridgeUserRelation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    AppUser appUser;
    @OneToOne(fetch = FetchType.EAGER)
    Fridge fridge;

    Boolean status;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }

    public FridgeUserRelation() {
    }

    public FridgeUserRelation(AppUser appUser, Fridge fridge, Boolean status) {
        this.appUser = appUser;
        this.fridge = fridge;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
