package com.hoon.springbestpractice.global.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {

    //TODO: Access Control

    @RequestMapping(value = "/health", method = RequestMethod.HEAD)
    public ResponseEntity<Object> checkApplicationHealth() {
        return ResponseEntity.ok().build();
    }
}
