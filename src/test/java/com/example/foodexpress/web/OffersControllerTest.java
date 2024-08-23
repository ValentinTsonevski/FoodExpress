package com.example.foodexpress.web;

import com.example.foodexpress.domain.dtos.offer.OfferDto;
import com.example.foodexpress.service.OfferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OffersControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void testOffersPageShown() throws Exception {
        mockMvc.perform(get("/offers/all")).
                andExpect(status().isOk()).
                andExpect(view().name("offers"));
    }


    @Test
    @Transactional
    @WithMockUser(username = "Admin21", roles = {"ADMIN"})
    void testOffersAddSuccess() throws Exception {
        mockMvc.perform(post("/offers/add").
                        param("id", "1").
                        param("description", "none").
                        param("name", "pizza").
                        param("price", "2.50").
                        param("weight", "500").
                        param("image_url", "asdasdads").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/offers/all"));
    }

    @Test
    @WithMockUser(username = "Admin21", roles = {"ADMIN"})
    void testOffersAddFail() throws Exception {
        mockMvc.perform(post("/offers/add").
                        param("id", "").
                        param("description", "none").
                        param("name", "pizza").
                        param("price", "2.50").
                        param("weight", "500").
                        param("image_url", "asdasdads").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/offers/all"));
    }






}