package com.example.foodexpress.domain.dtos.cart;

import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.user.UserDto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private Long id;

    private UserDto user;

    private List<OfferDto> offers;

    private BigDecimal totalPrice;

    public CartDto() {
    }

    public Long getId() {
        return id;
    }

    public CartDto setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDto getUser() {
        return user;
    }

    public CartDto setUser(UserDto user) {
        this.user = user;
        return this;
    }

    public List<OfferDto> getOffers() {
        return offers;
    }

    public CartDto setOffers(List<OfferDto> offers) {
        this.offers = offers;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CartDto setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
