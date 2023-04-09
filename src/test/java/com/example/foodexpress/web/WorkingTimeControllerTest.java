package com.example.foodexpress.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class WorkingTimeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testWorkingTimePageShown() throws Exception {
        mockMvc.perform(get("/working-time")).
                andExpect(status().isOk()).
                andExpect(view().name("working-time"));
    }

}