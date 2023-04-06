package com.example.foodexpress.domain.dtos.view;


public class OfferDetailsViewDto {
    private Long id;
    private String description;
    private String imageUrl;
    private String name;
    private double price;
    private double weight;


    public OfferDetailsViewDto() {
    }

    public Long getId() {
        return id;
    }

    public OfferDetailsViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailsViewDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailsViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public OfferDetailsViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public OfferDetailsViewDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public OfferDetailsViewDto setWeight(double weight) {
        this.weight = weight;
        return this;
    }
}
