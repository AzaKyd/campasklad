package com.campasklad.products.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    @GetMapping("/get-product")
    public String getProduct() {
        return "Hello World";
    }
}
