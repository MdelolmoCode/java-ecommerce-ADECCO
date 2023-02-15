package com.example.repositories;

import com.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    boolean existsById(Long id);
    List<Product> findByAvailableTrue();
    List<Product> findByPriceBetween(Double priceMin, Double priceMax);
    List<Product> findByStockLeftLessThan(Long amount);
    List<Product> findByCategories_Name(String name);
    List<Product> findByManufacturer_Cif(String cif);

}