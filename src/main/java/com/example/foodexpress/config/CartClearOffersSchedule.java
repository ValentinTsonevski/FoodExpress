package com.example.foodexpress.config;

import com.example.foodexpress.domain.entity.CartEntity;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.repository.CartRepository;
import com.example.foodexpress.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;


@Component
public class CartClearOffersSchedule {

    private final CartRepository cartRepository;
    private final CartService cartService;

    @Autowired
    public CartClearOffersSchedule(CartRepository cartRepository, CartService cartService ) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }


    @Scheduled(cron = "0 0 0 * * ?") // runs at midnight every day
    public void clearCartOffers() {
        List<CartEntity> carts = this.cartRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (CartEntity cart : carts) {

            if (cart.getOffers() != null) {
                Iterator<OfferEntity> iterator = cart.getOffers().iterator();
                while (iterator.hasNext()) {
                    OfferEntity offer = iterator.next();
                    iterator.remove();
                }
                cart.setTotalPrice(BigDecimal.ZERO);
                this.cartService.saveSchedule(cart);
            }
        }
    }

}
