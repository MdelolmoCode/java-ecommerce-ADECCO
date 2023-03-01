package com.example.repositories;

import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(Long orderNumber);
    Optional<Order> findByShoppingCart(ShoppingCart shoppingCart);
    List<Order> findAllByAddressCity(String city);
    Optional<Order> findByShoppingCartCustomer(Customer customer);
}