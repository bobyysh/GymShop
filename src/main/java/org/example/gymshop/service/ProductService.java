package org.example.gymshop.service;

import org.example.gymshop.repository.ProductRepository;
import org.example.gymshop.service.ProductService;
import org.example.gymshop.model.Product;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
