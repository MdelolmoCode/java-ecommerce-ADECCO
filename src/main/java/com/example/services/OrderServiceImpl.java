package com.example.services;

import com.example.entities.Order;
import com.example.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (id == null || id <= 0)
            return Optional.empty();
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> findByOrderNumber(Long orderNumber) {
        if (orderNumber == null)
            return Optional.empty();
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<Order> findByAddressCity(String city) {
        if (!StringUtils.hasLength(city))
            return new ArrayList<>();
        return orderRepository.findByAddressCity(city);
    }
}
