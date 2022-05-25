package com.emirleroglu.foodie.model;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long spoonId;

    private String aisle;

    public Ingredient() {
    }

    public Ingredient(String name, Long spoonId, String aisle) {
        this.name = name;
        this.spoonId = spoonId;
        this.aisle = aisle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
}
