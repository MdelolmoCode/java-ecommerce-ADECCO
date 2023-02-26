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
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Sql("Orders.sql")
    @Test
    void findAll() {
        List<Order> orderList = orderRepository.findAll();

        assertNotNull(orderList);
        assertEquals(6, orderList.size());
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

    @Sql("Orders.sql")
    @Test
    void findByShoppingCart() {
        var cart1 = shoppingCartRepository.findById(1L).get();
        Optional<Order> orderOpt = orderRepository.findByShoppingCart(cart1);
        assertTrue(orderOpt.isPresent());
    }

    @Sql("Orders.sql")
    @Test
    void findByShoppingCartCustomer() {
        var customer1 = customerRepository.findById(1L).get();
        Optional<Order> orderOpt = orderRepository.findByShoppingCartCustomer(customer1);
        assertTrue(orderOpt.isPresent());

        var customer3 = customerRepository.findById(3L).get(); // Customer sin ShoppingCart
        orderOpt = orderRepository.findByShoppingCartCustomer(customer3);
        assertTrue(orderOpt.isEmpty());
    }
}