package com.merobazar.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/api/health")
    public String health() {
        return "Welcome to MeroBazar Api.";
    }

}
