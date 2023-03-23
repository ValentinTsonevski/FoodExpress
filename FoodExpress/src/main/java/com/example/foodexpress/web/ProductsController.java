package com.example.foodexpress.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class ProductsController {

    @GetMapping("/products")
    private String getProducts(){

        return "products";
    }

//    @PostMapping("/products")
//    private String postProducts(Model model){
//
//
//
//    }
}
