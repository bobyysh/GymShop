package org.example.gymshop.controller;

import org.example.gymshop.model.Product;
import org.example.gymshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(@RequestParam(required = false) String sort,  Model model) {

        String sortType = (sort != null) ? sort : "asc";

        List<Product> products = productService.findAllProducts(sortType);
        model.addAttribute("products", products);
        model.addAttribute("sort", sortType);
        return "products";
    }

    @GetMapping("/products/category/{categoryName}")
    public String productsCategory(@PathVariable String categoryName, @RequestParam(required = false) String sort ,Model model) {
        String sortType = (sort != null) ? sort : "asc";

        List<Product> products = productService.findByCategory(categoryName, sortType);
        model.addAttribute("products", products);
        model.addAttribute("sort", sortType);
        return "products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }
    @PostMapping("/products/edit")
    public String editProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
