package org.example.gymshop.service;

import org.example.gymshop.repository.ProductRepository;
import org.example.gymshop.service.ProductService;
import org.example.gymshop.model.Product;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    private Sort createSort(String sortType){
        if("desc".equals(sortType)){
            return Sort.by("price").descending();
        }
        return Sort.by("price").ascending();
    }

    public List<Product> findAllProducts(String sortType) {
        return productRepository.findAll(createSort(sortType));
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

    public List<Product> findByCategory(String category, String sortType) {
        return productRepository.findByCategory(category, createSort(sortType));
    }

}
