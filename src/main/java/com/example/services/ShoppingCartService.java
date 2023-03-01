package com.example.services;

import com.example.entities.Customer;
import com.example.entities.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<ShoppingCart> findAll();
    Optional<ShoppingCart> findById(Long id);
    Optional<ShoppingCart> findByCustomer(Optional<Customer> customer);
    void deleteById(Long id);
    ShoppingCart save (ShoppingCart shoppingCart);
    void update(ShoppingCart shoppingCart);


    //Lógica negocio

    double calculateShoppingCartPrice(ShoppingCart shoppingCart);

}
