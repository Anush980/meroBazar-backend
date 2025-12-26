package com.merobazar.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/api/")
    public String getString(){
        return "Welcome to MeroBazar Api.";
    }

}
