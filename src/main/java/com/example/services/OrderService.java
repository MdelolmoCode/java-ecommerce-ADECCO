package com.example.services;

import com.example.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Optional<Order> findById(Long id);
    Optional<Order> findByOrderNumber(Long orderNumber);

}
