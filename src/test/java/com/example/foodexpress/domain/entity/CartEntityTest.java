package com.example.foodexpress.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartEntityTest {

    @Test
    public void testGetAndSetUser() {
        UserEntity user = new UserEntity();
        CartEntity cart = new CartEntity();
        cart.setUser(user);
        assertEquals(user, cart.getUser());
    }

    @Test
    public void testGetAndSetOffers() {
        OfferEntity offer1 = new OfferEntity();
        OfferEntity offer2 = new OfferEntity();
        List<OfferEntity> offers = new ArrayList<>();
        offers.add(offer1);
        offers.add(offer2);
        CartEntity cart = new CartEntity();
        cart.setOffers(offers);
        assertEquals(offers, cart.getOffers());
    }

    @Test
    public void testGetAndSetTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(25.0);
        CartEntity cart = new CartEntity();
        cart.setTotalPrice(totalPrice);
        assertEquals(totalPrice, cart.getTotalPrice());
    }
}