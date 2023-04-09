package com.example.foodexpress.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfferEntityTest {

    @Test
    public void testGettersAndSetters() {
        OfferEntity offer = new OfferEntity();
        offer.setId(1L);
        offer.setName("Pizza Margherita");
        offer.setPrice(9.99);
        offer.setWeight(500.0);
        offer.setDescription("Tomato sauce, mozzarella, and fresh basil.");
        offer.setImage_url("https://example.com/images/pizza_margherita.jpg");

        assertEquals(1L, offer.getId().longValue());
        assertEquals("Pizza Margherita", offer.getName());
        assertEquals(9.99, offer.getPrice(), 0.001);
        assertEquals(500.0, offer.getWeight(), 0.001);
        assertEquals("Tomato sauce, mozzarella, and fresh basil.", offer.getDescription());
        assertEquals("https://example.com/images/pizza_margherita.jpg", offer.getImage_url());
    }


}