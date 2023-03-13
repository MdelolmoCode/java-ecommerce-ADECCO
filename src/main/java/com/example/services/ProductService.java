package com.example.services;

import com.example.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findByIdWithCategories(Long id);

    // CRUD
    List<Product> findAll();
    boolean existsById(Long id);
    Optional<Product> findById(Long id);
    List<Product> findAllByNameContainsIgnoreCase(String name);
    // Optional<Product> findByName(String name);
    List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findAllByPriceGreaterThan(Double price);
    List<Product> findAllByPriceLessThan(Double price);
    List<Product> findAllByStockLeftLessThan(Long amount);
    List<Product> findAllByAvailableTrue();
    List<Product> findAllByCategories_Name(String category);
    List<Product> findAllByManufacturer_Cif(String cif);
    List<Product> findAllByCategories_MatureFalse();
    List<Product> findByCategories_Id(Long id);


    Product save(Product product); // crear nuevo producto
    Product update(Product product);
    void deleteById (Long id);
    void deleteAllById(List<Long> ids);
    void saveAll(List<Product> products);


    // BUSINESS LOGIC
    void addStock(Product product, Long amount);
    void removeStock(Product product, Long amount);
    boolean isAvailable(Product product); // comprobar si hay stock (stock !0)
    Product changeAvailability(Product product); // si hay 0 stock (isAvailable==0), cambiar a no disponible. y viceversa

}
