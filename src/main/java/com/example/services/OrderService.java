package com.example.services;

import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Optional<Order> findById(Long id);
    Optional<Order> findByOrderNumber(Long orderNumber);
    Optional<Order> findByShoppingCart(ShoppingCart shoppingCart);
    List<Order> findAllByAddressCity(String city);
    Optional<Order> findByCustomer(Optional<Customer> customer);

    Order save(Order order);
    Order update(Order order);
    void deleteById(Long id);

}
