package com.example.foodexpress.service.impl;

import com.example.foodexpress.customExceptions.ObjectNotFoundException;
import com.example.foodexpress.domain.dtos.model.OfferDetailDto;
import com.example.foodexpress.domain.dtos.view.OfferDetailsViewDto;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.repository.OfferRepository;
import com.example.foodexpress.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    public Page<OfferDetailsViewDto> getAllOffers(Pageable pageable) {
        return
                offerRepository.
                        findAll(pageable).
                        map(this::map);
    }

    @Override
    public OfferDetailDto findById(Long id) {
       return this.offerRepository.findById(id).map(offer ->
            this.modelMapper.map(offer,OfferDetailDto.class)).orElseThrow(() -> new  ObjectNotFoundException(id,"offer"));
    }

    @Override
    public OfferEntity getOfferById(Long id) {
        return this.offerRepository.findById(id).orElseThrow(() -> new  ObjectNotFoundException(id,"offer"));
    }


    @Override
    public void updateOffer(OfferEntity offer) {
        this.offerRepository.save(offer);
    }
    @Override
    public OfferEntity editOffer(OfferEntity offerEntity, OfferDetailDto offerDetailDto) {
        OfferEntity offer = this.modelMapper.map(offerDetailDto, OfferEntity.class);
        offer.setName(offerEntity.getName());
        offer.setPrice(offerEntity.getPrice());
        offer.setDescription(offerEntity.getDescription());
        offer.setWeight(offerEntity.getWeight());
        offer.setImage_url(offerEntity.getImage_url());
        return offer;
    }

    @Override
    public void saveOffer(OfferDetailDto offer) {
        OfferEntity offerToSave = this.modelMapper.map(offer, OfferEntity.class);
        this.offerRepository.save(offerToSave);
    }

    @Override
    public void deleteOfferById(Long id)  {
      OfferEntity offerEntity = offerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "offer"));

      offerRepository.delete(offerEntity);

    }

    private OfferDetailsViewDto map(OfferEntity offerEntity) {

        return new OfferDetailsViewDto().setId(offerEntity.getId())
                .setDescription(offerEntity.getDescription())
                .setImageUrl(offerEntity.getImage_url())
                .setName(offerEntity.getName())
                .setWeight(offerEntity.getWeight())
                .setPrice(offerEntity.getPrice());

    }

}
