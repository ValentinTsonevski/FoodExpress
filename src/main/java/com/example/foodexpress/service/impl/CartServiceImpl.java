package com.example.foodexpress.service.impl;

import com.example.foodexpress.domain.dtos.cart.CartDto;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.domain.entity.CartEntity;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.repository.CartRepository;
import com.example.foodexpress.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CartDto save(CartDto cartDto) {
        CartEntity cartEntity = mapCartDtoToEntity(cartDto);
        this.cartRepository.saveAndFlush(cartEntity);
        return mapCartEntityToDto(cartEntity);
    }

    @Override
    public void addOffersToCart(CartDto cart, OfferDto offer) {
        if (cart == null || offer == null) {
            return;
        }

        List<OfferDto> offerDtoList = cart.getOffers();
        if (offerDtoList == null) {
            offerDtoList = new ArrayList<>();
        }

        offerDtoList.add(offer);
        cart.setOffers(offerDtoList);
    }

    @Override
    public void removeOffer(UserDto currentUser, OfferDto offerToRemove) {
        CartDto cart = currentUser.getCart();
        if (cart != null) {
            List<OfferDto> offers = cart.getOffers();
            if (offers != null && !offers.isEmpty()) {
                if (offers.removeIf(offer -> offer.getId().equals(offerToRemove.getId()))) {
                    save(cart);
                }

            }
        }
    }

    @Override
    public BigDecimal calculateTotalPrice(CartDto cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OfferDto offer : cart.getOffers()) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(offer.getPrice()));
        }
        return totalPrice;
    }

    @Override
    public void clearCart(CartDto cartDto) {
        cartDto.setOffers(new ArrayList<>());
        cartDto.setTotalPrice(BigDecimal.ZERO);

        CartEntity cartEntity = mapCartDtoToEntity(cartDto);
        cartRepository.saveAndFlush(cartEntity);
    }

    @Override
    public CartDto findCartByUser(UserDto userDto) {
        UserEntity userEntity = this.modelMapper.map(userDto, UserEntity.class);
        CartEntity cartEntityByUser = this.cartRepository.findCartEntityByUser(userEntity);

        return mapCartEntityToDto(cartEntityByUser);
    }

    @Override
    public void saveSchedule(CartEntity cartEntity) {
        this.cartRepository.save(cartEntity);
    }


    private CartEntity mapCartDtoToEntity(CartDto cartDto) {
        return this.modelMapper.map(cartDto, CartEntity.class);
    }

    private CartDto mapCartEntityToDto(CartEntity cartEntity) {
        return this.modelMapper.map(cartEntity, CartDto.class);
    }



}
