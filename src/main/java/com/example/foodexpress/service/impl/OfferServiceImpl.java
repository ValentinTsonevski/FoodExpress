package com.example.foodexpress.service.impl;

import com.example.foodexpress.customExceptions.ObjectNotFoundException;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.dtos.offer.OfferDetailsViewDto;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.repository.OfferRepository;
import com.example.foodexpress.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public OfferDto findById(Long id) {
        return this.offerRepository.findById(id).map(this::mapOfferEntityToDto).orElseThrow(() -> new ObjectNotFoundException(id, "offer"));
    }


    @Override
    public void editOffer(OfferDto offerDto, Long id) {

        OfferEntity offerEntity = this.offerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "offer"));

        offerEntity.setName(offerDto.getName());
        offerEntity.setPrice(offerDto.getPrice());
        offerEntity.setDescription(offerDto.getDescription());
        offerEntity.setWeight(offerDto.getWeight());
        offerEntity.setImage_url(offerDto.getImage_url());

        this.offerRepository.save(offerEntity);
        mapOfferEntityToDto(offerEntity);
    }

    @Override
    public void saveOffer(OfferDto offerDto) {
        OfferEntity offerToSave = this.modelMapper.map(offerDto, OfferEntity.class);
        this.offerRepository.save(offerToSave);
    }

    @Override
    public void deleteOfferById(Long id) {
        OfferEntity offerEntity = offerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "offer"));

        offerRepository.delete(offerEntity);
    }



    private OfferDetailsViewDto map(OfferEntity offerEntity) {

        return new OfferDetailsViewDto().setId(offerEntity.getId())
                .setDescription(offerEntity.getDescription())
                .setImage_url(offerEntity.getImage_url())
                .setName(offerEntity.getName())
                .setWeight(offerEntity.getWeight())
                .setPrice(offerEntity.getPrice());

    }

    private OfferDto mapOfferEntityToDto(OfferEntity offerEntity) {
        return this.modelMapper.map(offerEntity, OfferDto.class);
    }

}
