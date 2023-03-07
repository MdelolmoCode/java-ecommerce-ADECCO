package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Optional<Order> findById(Long id);
    Optional<Order> findByOrderNumber(Long orderNumber);
    List<Order> findAllByAddressCity(String city);
    Optional<Order> findByCustomer(Customer customer);

    double calculateTotalPrice(List<CartItem> cartItems);
    void emptyCart(ShoppingCart shoppingCart);
    long getAvailableOrderNumber();

    Order save(Order order);
    Order update(Order order);
    void deleteById(Long id);

}
