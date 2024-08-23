package com.example.foodexpress.domain.dtos.offer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferDetailsViewDtoTest {


    @Test
    public void testGetters() {
        OfferDetailsViewDto offer = new OfferDetailsViewDto()
                .setId(1L)
                .setDescription("Delicious pizza")
                .setImage_url("https://example.com/pizza.jpg")
                .setName("Pizza")
                .setPrice(10.99)
                .setWeight(500.0);

        assertEquals(1L, offer.getId());
        assertEquals("Delicious pizza", offer.getDescription());
        assertEquals("https://example.com/pizza.jpg", offer.getImage_url());
        assertEquals("Pizza", offer.getName());
        assertEquals(10.99, offer.getPrice());
        assertEquals(500.0, offer.getWeight());
    }

    @Test
    public void testSetters() {
        OfferDetailsViewDto offer = new OfferDetailsViewDto();

        offer.setId(1L);
        assertEquals(1L, offer.getId());

        offer.setDescription("Delicious pizza");
        assertEquals("Delicious pizza", offer.getDescription());

        offer.setImage_url("https://example.com/pizza.jpg");
        assertEquals("https://example.com/pizza.jpg", offer.getImage_url());

        offer.setName("Pizza");
        assertEquals("Pizza", offer.getName());

        offer.setPrice(10.99);
        assertEquals(10.99, offer.getPrice());

        offer.setWeight(500.0);
        assertEquals(500.0, offer.getWeight());
    }



}