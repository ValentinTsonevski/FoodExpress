package com.example.foodexpress.service;

import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.offer.OfferDetailsViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {
    Page<OfferDetailsViewDto> getAllOffers(Pageable pageable);

    OfferDto findById(Long id);

    void editOffer(OfferDto offerDto, Long id);

    void saveOffer(OfferDto offer);

    void deleteOfferById(Long id);



}
