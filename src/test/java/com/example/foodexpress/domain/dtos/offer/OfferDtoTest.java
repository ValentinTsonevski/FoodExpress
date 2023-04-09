package com.example.foodexpress.domain.dtos.offer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferDtoTest {


    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String description = "Delicious food";
        String name = "Pizza";
        double price = 10.0;
        double weight = 0.5;
        String imageUrl = "https://example.com/image.jpg";

        OfferDto offer = new OfferDto()
                .setId(id)
                .setDescription(description)
                .setName(name)
                .setPrice(price)
                .setWeight(weight)
                .setImage_url(imageUrl);

        assertEquals(id, offer.getId());
        assertEquals(description, offer.getDescription());
        assertEquals(name, offer.getName());
        assertEquals(price, offer.getPrice());
        assertEquals(weight, offer.getWeight());
        assertEquals(imageUrl, offer.getImage_url());
    }

    @Test
    public void testConstructor() {
        Long id = 1L;
        String description = "Delicious food";
        String name = "Pizza";
        double price = 10.0;
        double weight = 0.5;
        String imageUrl = "https://example.com/image.jpg";

        OfferDto offer = new OfferDto();

        assertEquals(id, offer.getId());
        assertEquals(description, offer.getDescription());
        assertEquals(name, offer.getName());
        assertEquals(price, offer.getPrice());
        assertEquals(weight, offer.getWeight());
        assertEquals(imageUrl, offer.getImage_url());
    }

}