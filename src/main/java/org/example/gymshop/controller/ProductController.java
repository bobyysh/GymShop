package org.example.gymshop.controller;

import org.example.gymshop.model.Product;
import org.example.gymshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
