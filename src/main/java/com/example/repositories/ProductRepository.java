package com.example.repositories;

import com.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Optional<Product> findByName(String name);
    List<Product> findAllByNameContainsIgnoreCase(String name);
    boolean existsById(Long id);
    List<Product> findAllByAvailableTrue();
    List<Product> findAllByPriceBetween(Double priceMin, Double priceMax);
    List<Product> findAllByPriceGreaterThan(Double price);
    List<Product> findAllByPriceLessThan(Double price);
    List<Product> findAllByStockLessThan(Long amount);
    List<Product> findAllByCategories_Name(String name);
    List<Product> findAllByManufacturer_Cif(String cif);
    List<Product> findAllByCategories_MatureFalse();


}