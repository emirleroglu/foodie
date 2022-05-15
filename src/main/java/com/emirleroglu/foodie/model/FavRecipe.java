package com.emirleroglu.foodie.model;

import javax.persistence.*;
import java.net.URL;

@Entity
public class FavRecipe {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long spoonApiId;
    @ManyToOne
    private AppUser appUser;

    private String imageURL;

    public FavRecipe() {
    }

    public FavRecipe(String title, Long spoonApiId, AppUser appUser, String imageURL) {
        this.title = title;
        this.spoonApiId = spoonApiId;
        this.appUser = appUser;
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSpoonApiId() {
        return spoonApiId;
    }

    public void setSpoonApiId(Long spoonApiId) {
        this.spoonApiId = spoonApiId;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
