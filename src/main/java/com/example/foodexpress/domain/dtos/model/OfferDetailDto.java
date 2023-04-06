package com.example.foodexpress.domain.dtos.model;

import jakarta.validation.constraints.NotNull;

public class OfferDetailDto {
    private Long id;
    private String description;
    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private int weight;
    @NotNull
    private String image_url;

    public OfferDetailDto() {
    }

    public Long getId() {
        return id;
    }

    public OfferDetailDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public OfferDetailDto setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public OfferDetailDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public OfferDetailDto setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public OfferDetailDto setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }
}
