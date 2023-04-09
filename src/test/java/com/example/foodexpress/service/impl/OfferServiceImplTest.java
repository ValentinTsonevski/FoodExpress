package com.example.foodexpress.service.impl;

import com.example.foodexpress.customExceptions.ObjectNotFoundException;
import com.example.foodexpress.domain.dtos.offer.OfferDetailsViewDto;
import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.domain.entity.OfferEntity;
import com.example.foodexpress.repository.OfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferServiceImpl offerService;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    void editOffer() {

        Long offerId = 1L;
        OfferDto offerDto = new OfferDto();
        offerDto.setName("New Offer");
        offerDto.setPrice(10.0);
        offerDto.setDescription("New Offer Description");
        offerDto.setWeight(100);
        offerDto.setImage_url("https://example.com/new_offer.jpg");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(offerId);
        offerEntity.setName("Old Offer");
        offerEntity.setPrice(5.0);
        offerEntity.setDescription("Old Offer Description");
        offerEntity.setWeight(50.0);
        offerEntity.setImage_url("https://example.com/old_offer.jpg");

        when(offerRepository.findById(offerId)).thenReturn(java.util.Optional.of(offerEntity));
        when(offerRepository.save(any(OfferEntity.class))).thenAnswer(i -> i.getArgument(0));


        offerService.editOffer(offerDto, offerId);


        verify(offerRepository, times(1)).findById(offerId);
        verify(offerRepository, times(1)).save(offerEntity);

        assertEquals(offerDto.getName(), offerEntity.getName());
        assertEquals(offerDto.getPrice(), offerEntity.getPrice());
        assertEquals(offerDto.getDescription(), offerEntity.getDescription());
        assertEquals(offerDto.getWeight(), offerEntity.getWeight());
        assertEquals(offerDto.getImage_url(), offerEntity.getImage_url());
    }

    @Test
    void testEditOfferThrowsException() {
        Long offerId = 1L;
        OfferDto offerDto = new OfferDto();

        when(offerRepository.findById(offerId)).thenReturn(java.util.Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> offerService.editOffer(offerDto, offerId));

        verify(offerRepository, times(1)).findById(offerId);
        verify(offerRepository, never()).save(any(OfferEntity.class));

    }


    @Test
    void deleteOfferById() {
        Long offerId = 1L;
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(offerId);
        when(offerRepository.findById(offerId)).thenReturn(Optional.of(offerEntity));

        offerService.deleteOfferById(offerId);

        verify(offerRepository, times(1)).findById(offerId);
        verify(offerRepository, times(1)).delete(offerEntity);
    }


    @Test
    void map_ShouldReturnCorrectOfferDetailsViewDto() {

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(1L);
        offerEntity.setName("Test Offer");
        offerEntity.setPrice(10.0);
        offerEntity.setDescription("Test Offer Description");
        offerEntity.setWeight(100);
        offerEntity.setImage_url("https://example.com/test_offer.jpg");

        OfferServiceImpl offerService = new OfferServiceImpl(null, new ModelMapper());


        OfferDetailsViewDto offerDetailsViewDto = offerService.map(offerEntity);


        assertNotNull(offerDetailsViewDto);
        assertEquals(offerEntity.getId(), offerDetailsViewDto.getId());
        assertEquals(offerEntity.getName(), offerDetailsViewDto.getName());
        assertEquals(offerEntity.getPrice(), offerDetailsViewDto.getPrice());
        assertEquals(offerEntity.getDescription(), offerDetailsViewDto.getDescription());
        assertEquals(offerEntity.getWeight(), offerDetailsViewDto.getWeight());
        assertEquals(offerEntity.getImage_url(), offerDetailsViewDto.getImage_url());
    }



    @Test
    void findById_shouldThrowObjectNotFoundException_whenOfferDoesNotExist() {
        Long offerId = 1L;

        when(offerRepository.findById(offerId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> offerService.findById(offerId));
        verify(offerRepository, times(1)).findById(offerId);
    }


}