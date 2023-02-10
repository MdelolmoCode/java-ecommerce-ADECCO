package com.example.services;

import com.example.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    // CRUD
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByPriceLessThan(Double price);
    List<Product> findByStockLeftLessThan(Long amount);
    List<Product> findByAvailableTrue();
    List<Product> findByCategories_Name(String name);
    List<Product> findByManufacturer_Cif(String cif);

    Product save(Product product); // crear nuevo producto
    Product update(Product product);
    void deleteById (Long id);


    // BUSINESS LOGIC
    void addStock(Product product, Long amount);
    void removeStock(Product product, Long amount);
    boolean isAvailable(Product product); // comprobar si hay stock (stock !0)
    Product changeAvailability(Product product); // si hay 0 stock (isAvailable==0), cambiar a no disponible. y viceversa

}
