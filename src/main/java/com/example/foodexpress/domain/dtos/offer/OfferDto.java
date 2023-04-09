package com.example.foodexpress.domain.dtos.offer;

public class OfferDto {
    private Long id;
    private String description;

    private String name;

    private double price;

    private double weight;

    private String image_url;

    public OfferDto() {
    }

    public Long getId() {
        return id;
    }

    public OfferDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public OfferDto setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public OfferDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public OfferDto setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public String getImage_url() {
        return image_url;
    }

    public OfferDto setImage_url(String image_url) {
        this.image_url = image_url;
        return this;
    }
}
