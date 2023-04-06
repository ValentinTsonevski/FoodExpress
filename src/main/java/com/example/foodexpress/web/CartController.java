package com.example.foodexpress.web;

import com.example.foodexpress.domain.entity.CartEntity;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.domain.entity.UserEntity;
import com.example.foodexpress.service.CartService;
import com.example.foodexpress.service.OfferService;
import com.example.foodexpress.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.example.foodexpress.outputs.SuccessMessages.*;


@Controller
@RequestMapping
public class CartController {

    private final OfferService offerService;
    private final UserService userService;
    private final CartService cartService;

    public CartController(OfferService offerService, UserService userService, CartService cartService) {
        this.offerService = offerService;
        this.userService = userService;
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String getCart(Model model, Principal principal) {

        String currentUserEmail = principal.getName();
        UserEntity currentUser = this.userService.getUserByEmail(currentUserEmail);

        CartEntity cart = currentUser.getCart();
        List<OfferEntity> offers = new ArrayList<>();

        if (cart != null) {
            offers = cart.getOffers();
            cart.setTotalPrice(cartService.calculateTotalPrice(cart));
            model.addAttribute("totalPrice", cart.getTotalPrice());
        } else {
            model.addAttribute("totalPrice", BigDecimal.ZERO);
        }

        model.addAttribute("offers", offers);
        return "cart";
    }

    @PostMapping("/cart/addOffer/{id}")
    public String addOfferToCart(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {
        String currentUserEmail = principal.getName();
        UserEntity currentUser = this.userService.getUserByEmail(currentUserEmail);
        OfferEntity offerToAdd = this.offerService.getOfferById(id);

        CartEntity cart = currentUser.getCart();
        if (cart == null) {
            cart = new CartEntity();
            currentUser.setCart(cart);
            cart.setUser(currentUser);
        }


        this.cartService.addOffersToCart(cart, offerToAdd);
        this.cartService.save(cart);

        redirectAttributes.addFlashAttribute("successMessage", offerToAdd.getName() + OFFER_ADDED);
        return "redirect:/cart";
    }


    @PostMapping("/cart/removeOffer/{id}")
    public String removeOfferFromCart(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {
        String currentUserEmail = principal.getName();
        UserEntity currentUser = this.userService.getUserByEmail(currentUserEmail);
        OfferEntity offerToRemove = this.offerService.getOfferById(id);

        this.cartService.removeOffer(currentUser, offerToRemove);

        redirectAttributes.addFlashAttribute("successMessage", offerToRemove.getName() + OFFER_REMOVE);
        return "redirect:/cart";
    }


    @Transactional
    @PostMapping("/cart/buy")
    public String buyCart(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        UserEntity user = userService.getUserByEmail(email);
        CartEntity cart = cartService.findCartByUser(user);

        cartService.clearCart(cart);
        model.addAttribute("cart", cart);

        redirectAttributes.addFlashAttribute("successMessage", BOUGHT_OFFERS);
        return "redirect:/cart";
    }


}
