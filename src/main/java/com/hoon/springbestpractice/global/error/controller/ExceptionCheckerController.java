package com.hoon.springbestpractice.global.error.controller;

import com.hoon.springbestpractice.global.error.EntityNotFoundException;
import com.hoon.springbestpractice.global.error.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExceptionCheckerController {

    @GetMapping("/exception")
    public ResponseEntity<Object> generateException() throws Exception {
        throw new Exception();
    }

    @GetMapping("/business/exception")
    public ResponseEntity<Object>  generateBusinessException() throws EntityNotFoundException {
        throw new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getMessage());
    }
}
