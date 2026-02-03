package org.example.gymshop.repository;

import org.example.gymshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
