package com.example.foodexpress.service.impl;

import com.example.foodexpress.domain.entity.CartEntity;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.repository.CartRepository;
import com.example.foodexpress.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartEntity save(CartEntity cart) {
       return this.cartRepository.saveAndFlush(cart) ;
    }

    @Override
    public void addOffersToCart(CartEntity cart, OfferEntity offer) {
        if (cart == null || offer == null) {
            return;
        }

        List<OfferEntity> offerEntityList = cart.getOffers();
        if (offerEntityList == null) {
            offerEntityList = new ArrayList<>();
        }

        offerEntityList.add(offer);
        cart.setOffers(offerEntityList);
    }

    @Override
    public void removeOffer(UserEntity currentUser, OfferEntity offerToRemove) {
        CartEntity cart = currentUser.getCart();
        if (cart != null) {
            List<OfferEntity> offers = cart.getOffers();
            if (offers != null && !offers.isEmpty()) {
                if (offers.removeIf(offer -> offer.getId().equals(offerToRemove.getId()))) {
                    save(cart);
                }
            }
        }
    }

    @Override
    public BigDecimal calculateTotalPrice(CartEntity cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OfferEntity offer : cart.getOffers()) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(offer.getPrice()));
        }
        return totalPrice;
    }

    @Override
    public void clearCart(CartEntity cart) {
        cart.setOffers(new ArrayList<>());
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.saveAndFlush(cart);
    }
    @Override
    public CartEntity findCartByUser(UserEntity user){
        return this.cartRepository.findCartEntityByUser(user);
    }



}
