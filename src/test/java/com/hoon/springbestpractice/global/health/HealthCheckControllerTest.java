package com.hoon.springbestpractice.global.health;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCHeckTest() throws Exception {
        // given

        // when
        ResultActions resultActions = mockMvc
                .perform(head("/health")
                        .contentType(MediaType.APPLICATION_JSON));


        // then
        resultActions
                .andExpect(status().is2xxSuccessful());
    }

}