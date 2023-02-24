package com.example.repositories;

import com.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Optional<Product> findByName(String name);
    @Query("select product from Product product left join fetch product.categories where product.id = :id")
    Optional<Product> findByIdWithCategories(Long id);
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
    List<Product> findByCategories_Id(Long id);



}