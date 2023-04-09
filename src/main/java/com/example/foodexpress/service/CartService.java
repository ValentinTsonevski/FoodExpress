package com.example.foodexpress.service;

import com.example.foodexpress.domain.dtos.cart.CartDto;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.domain.entity.CartEntity;

import java.math.BigDecimal;

public interface CartService {

 CartDto save(CartDto cart);

 void addOffersToCart(CartDto cart, OfferDto offer);

 void removeOffer(UserDto currentUser, OfferDto offerToRemove);


 BigDecimal calculateTotalPrice(CartDto cart);

 void clearCart(CartDto cart);

 CartDto findCartByUser(UserDto user);

 void saveSchedule(CartEntity cartEntity);
}
