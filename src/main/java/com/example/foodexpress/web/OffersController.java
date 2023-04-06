package com.example.foodexpress.web;

import com.example.foodexpress.customExceptions.ObjectNotFoundException;
import com.example.foodexpress.customExceptions.OfferNotFoundException;
import com.example.foodexpress.domain.dtos.model.OfferDetailDto;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.foodexpress.outputs.SuccessMessages.*;


@Controller
@RequestMapping("/offers")
public class OffersController {
    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String getAllOffers(Model model,
                               @PageableDefault(
                                       sort = "id",
                                       size = 3
                               ) Pageable pageable) {

        var allOffersPage = offerService.getAllOffers(pageable);

        model.addAttribute("offers", allOffersPage);

        return "offers";
    }

    @GetMapping("/{id}")
    public String getOfferDetail(@PathVariable("id") Long id,
                                 Model model) {

        var offerDto =
                offerService.findById(id);

        model.addAttribute("offer", offerDto);

        return "details";

    }


    @GetMapping("/add")
    public String addOffer(Model model) {
        model.addAttribute("offer", new OfferDetailDto());

        return "add_offer";
    }

    @PostMapping("/add")
    public String saveOffer(@Valid OfferDetailDto offer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offer", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer",
                    bindingResult);
            return "redirect:/offers/add";
        }
        redirectAttributes.addFlashAttribute("successMessage", offer.getName() + OFFER_ADDED);
        offerService.saveOffer(offer);
        return "redirect:/offers/all";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,Model model) {
        OfferEntity offerEntity = this.offerService.getOfferById(id);
        if(offerEntity == null){
            throw new ObjectNotFoundException(id,"offer");
        }
        model.addAttribute("itemId",id);
        model.addAttribute("itemType",offerEntity);

            offerService.deleteOfferById(id);

            redirectAttributes.addFlashAttribute("successMessage", OFFER_DELETED);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        OfferDetailDto offer = this.offerService.findById(id);

        if (offer == null) {
            throw new ObjectNotFoundException(id, "offer");
        }
        model.addAttribute("itemId", id);
        model.addAttribute("itemType", offer);

        model.addAttribute("offer", offer);

        return "edit_offer";
    }


    @PostMapping("/{id}/edit")
    public String editOffer(@ModelAttribute("offer") OfferEntity offerEntity, RedirectAttributes redirectAttributes) {
        Long id = offerEntity.getId();
        OfferDetailDto seriesDTO = this.offerService.findById(id);

        OfferEntity offer = this.offerService.editOffer(offerEntity, seriesDTO);
        this.offerService.updateOffer(offer);

        redirectAttributes.addFlashAttribute("successMessage", offer.getName() + OFFER_EDITED);
        return "redirect:/offers/all";

    }


}
