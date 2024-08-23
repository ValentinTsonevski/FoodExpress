package com.example.foodexpress.domain.dtos.cart;

import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.user.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class CartDtoTest {

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        UserDto user = new UserDto().setId(2L).setUsername("testuser");
        OfferDto offer1 = new OfferDto().setId(3L).setName("offer1").setPrice(10.0).setWeight(0.2);
        OfferDto offer2 = new OfferDto().setId(4L).setName("offer2").setPrice(20.0).setWeight(0.3);
        List<OfferDto> offers = new ArrayList<>();
        offers.add(offer1);
        offers.add(offer2);
        BigDecimal totalPrice = new BigDecimal(30.0);

        CartDto cartDto = new CartDto()
                .setId(id)
                .setUser(user)
                .setOffers(offers)
                .setTotalPrice(totalPrice);

        Assertions.assertEquals(id, cartDto.getId());
        Assertions.assertEquals(user, cartDto.getUser());
        Assertions.assertEquals(offers, cartDto.getOffers());
        Assertions.assertEquals(totalPrice, cartDto.getTotalPrice());
    }

}