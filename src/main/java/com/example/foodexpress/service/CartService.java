package com.example.foodexpress.service;

import com.example.foodexpress.domain.entity.CartEntity;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.domain.entity.UserEntity;

import java.math.BigDecimal;

public interface CartService {

 CartEntity save(CartEntity cart);

 void addOffersToCart(CartEntity cart,OfferEntity offer);

 void removeOffer(UserEntity currentUser, OfferEntity offerToRemove);


 BigDecimal calculateTotalPrice(CartEntity cart);

 void clearCart(CartEntity cart);

 CartEntity findCartByUser(UserEntity user);
}
