package com.example.repositories;

import com.example.entities.Customer;
import com.example.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByCustomer(Customer customer);
}