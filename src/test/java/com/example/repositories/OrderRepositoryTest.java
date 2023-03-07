package com.example.repositories;

import com.example.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Sql("Orders.sql")
    @Test
    void findAll() {
        List<Order> orderList = orderRepository.findAll();

        assertNotNull(orderList);
        assertEquals(6, orderList.size());
    }

    @Sql("Orders.sql")
    @Test
    void findById() {
        assertTrue(orderRepository.findById(1L).isPresent());
        assertTrue(orderRepository.findById(999L).isEmpty());
    }

    @Sql("Orders.sql")
    @Test
    void findByOrderNumber() {
        Optional<Order> orderOpt = orderRepository.findByOrderNumber(2000L);
        assertTrue(orderOpt.isPresent());

        orderOpt = orderRepository.findByOrderNumber(9999999L);
        assertTrue(orderOpt.isEmpty());

        orderOpt = orderRepository.findByOrderNumber(-5L);
        assertTrue(orderOpt.isEmpty());

        orderOpt = orderRepository.findByOrderNumber(null);
        assertTrue(orderOpt.isEmpty());
    }

}