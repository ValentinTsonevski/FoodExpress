package com.example.foodexpress.service;

import com.example.foodexpress.customExceptions.OfferNotFoundException;
import com.example.foodexpress.domain.dtos.model.OfferDetailDto;
import com.example.foodexpress.domain.dtos.view.OfferDetailsViewDto;
import com.example.foodexpress.domain.entity.OfferEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OfferService {
    Page<OfferDetailsViewDto> getAllOffers(Pageable pageable);

    OfferDetailDto findById(Long id);
   OfferEntity getOfferById(Long id);
     void updateOffer(OfferEntity offer);
    OfferEntity editOffer(OfferEntity offerEntity, OfferDetailDto offerDetailDto);
    void saveOffer(OfferDetailDto offer);

     void deleteOfferById(Long id) ;

}
