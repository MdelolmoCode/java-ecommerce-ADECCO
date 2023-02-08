package com.example.services;

import com.example.entities.Product;

import java.util.List;

public interface ProductService {

    // CRUD
    List<Product> findAll();
    List<Product> findAllById(Long id);
    List<Product> findAllByName(String name);
    List<Product> findAllByCategory(String category);
    List<Product> findAllByPriceGreaterThan(Double price);
    List<Product> findAllByPriceLessThan(Double price);
    List<Product> findAllByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findAllByManufacturerId(Long id);
    List<Product> findAllAvailable();
    Product save(Product product);
    Product update(Product product);
    void deleteById(Long id);

    // LÓGICA DE NEGOCIO
    boolean addStock(Product product, Long amount);
    boolean removeStock(Product product, Long amount);
    boolean isAvailable(Product product); // comprobar si hay stock (stock !0)




}
