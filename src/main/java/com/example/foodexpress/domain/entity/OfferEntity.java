package com.example.foodexpress.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int weight;

    @Column
    private String description;

    @Column(nullable = false)
    private String image_url;


    public OfferEntity() {
    }

    public String getName() {
        return name;
    }

    public OfferEntity setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public OfferEntity setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public OfferEntity setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public OfferEntity setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }
}
