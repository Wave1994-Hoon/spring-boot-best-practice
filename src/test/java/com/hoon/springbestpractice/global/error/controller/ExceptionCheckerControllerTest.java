package com.hoon.springbestpractice.global.error.controller;

import com.hoon.springbestpractice.global.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ExceptionCheckerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void exceptionTest() throws Exception {
        // given

        // when
        ResultActions resultActions = mockMvc
                .perform(get("/exception")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());

        // then
        resultActions
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("status").value(ErrorCode.INTERNAL_SERVER_ERROR.getStatus()))
                .andExpect(jsonPath("message").value(ErrorCode.INTERNAL_SERVER_ERROR.getMessage()))
        ;
    }

    @Test
    void businessExceptionTest() throws Exception {
        // given

        // when
        ResultActions resultActions = mockMvc
                .perform(get("/business/exception")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());

        // then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("status").value(ErrorCode.ENTITY_NOT_FOUND.getStatus()))
                .andExpect(jsonPath("message").value(ErrorCode.ENTITY_NOT_FOUND.getMessage()))
        ;
    }


}