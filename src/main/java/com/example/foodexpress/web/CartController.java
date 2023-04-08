package com.example.foodexpress.web;

import com.example.foodexpress.domain.dtos.cart.CartDto;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.user.UserDto;
import com.example.foodexpress.service.CartService;
import com.example.foodexpress.service.OfferService;
import com.example.foodexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CartController(OfferService offerService, UserService userService, CartService cartService) {
        this.offerService = offerService;
        this.userService = userService;
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String getCart(Model model, Principal principal) {

        String currentUserEmail = principal.getName();
        UserDto currentUser = this.userService.getUserByEmail(currentUserEmail);

        CartDto cart = currentUser.getCart();
        List<OfferDto> offers = new ArrayList<>();

        if (cart != null) {
            offers = cart.getOffers();

            cart.setTotalPrice(cartService.calculateTotalPrice(cart));
            this.cartService.save(cart);

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
        UserDto currentUser = this.userService.getUserByEmail(currentUserEmail);

        OfferDto offerToAdd = this.offerService.findById(id);

        CartDto cart = currentUser.getCart();
        if (cart == null) {
            cart = new CartDto();
            currentUser.setCart(cart);
            cart.setUser(currentUser);
        }

        this.cartService.addOffersToCart(cart, offerToAdd);

        if(cart.getTotalPrice() == null || cart.getTotalPrice().equals(BigDecimal.ZERO)){
            cart.setTotalPrice(this.cartService.calculateTotalPrice(currentUser.getCart()));
        }

        this.cartService.save(cart);

        redirectAttributes.addFlashAttribute("successMessage", offerToAdd.getName() + OFFER_ADDED);
        return "redirect:/cart";
    }


    @PostMapping("/cart/removeOffer/{id}")
    public String removeOfferFromCart(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes) {
        String currentUserEmail = principal.getName();
        UserDto currentUser = this.userService.getUserByEmail(currentUserEmail);
        OfferDto offerToRemove = this.offerService.findById(id);

        this.cartService.removeOffer(currentUser, offerToRemove);

        redirectAttributes.addFlashAttribute("successMessage", offerToRemove.getName() + OFFER_REMOVE);
        return "redirect:/cart";
    }


    @Transactional
    @PostMapping("/cart/buy")
    public String buyCart(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        UserDto user = userService.getUserByEmail(email);
        CartDto cart = cartService.findCartByUser(user);

        cartService.clearCart(cart);
        cart.setTotalPrice(BigDecimal.ZERO);

        model.addAttribute("cart", cart);

        redirectAttributes.addFlashAttribute("successMessage", BOUGHT_OFFERS);
        return "redirect:/cart";
    }


}
