package com.example.foodexpress.service.impl;

import com.example.foodexpress.domain.dtos.cart.CartDto;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartDto cartDto;


    @InjectMocks
    private CartServiceImpl cartService;

        @Test
        void calculateTotalPrice() {
            List<OfferDto> offerDtoList = new ArrayList<>();
            OfferDto offer1 = new OfferDto();
            offer1.setPrice(5.00);
            offerDtoList.add(offer1);
            OfferDto offer2 = new OfferDto();
            offer2.setPrice(10.00);
            offerDtoList.add(offer2);

            when(cartDto.getOffers()).thenReturn(offerDtoList);

            BigDecimal totalPrice = cartService.calculateTotalPrice(cartDto);

            assertEquals(BigDecimal.valueOf(15.00), totalPrice);
        }


}


