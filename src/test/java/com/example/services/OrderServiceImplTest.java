package com.example.services;

import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.ShoppingCart;
import com.example.entities.enums.PaymentMethod;
import com.example.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    private OrderServiceImpl orderService;
    private OrderRepository orderRepo;

    @BeforeEach
    void setUp() {
        orderRepo = mock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepo);
    }

    @Test
    void findAll() {
        List<Order> ordersDB = new ArrayList<>(List.of(
                new Order(1L, 1000L, null, null, PaymentMethod.CREDIT_CARD),
                new Order(2L, 2000L, null, null, PaymentMethod.CREDIT_CARD),
                new Order(3L, 3000L, null, null, PaymentMethod.DEBIT_CARD)
        ));
        when(orderRepo.findAll()).thenReturn(ordersDB);

        List<Order> orders = orderService.findAll();

        assertNotNull(orders);
        assertEquals(3, orders.size());
        verify(orderRepo).findAll();
    }

    @Test
    void findAllEmpty() {
        when(orderRepo.findAll()).thenReturn(new ArrayList<>());

        List<Order> orders = orderService.findAll();

        assertNotNull(orders);
        assertEquals(0, orders.size());
        verify(orderRepo).findAll();
    }

    @Test
    void findById() {
        Order orderDB = new Order(1L, 1000L, null, null, PaymentMethod.CREDIT_CARD);
        when(orderRepo.findById(1L)).thenReturn(Optional.of(orderDB));
        when(orderRepo.findById(999L)).thenReturn(Optional.empty());

        Optional<Order> orderDBOpt = orderService.findById(1L);
        assertTrue(orderDBOpt.isPresent());
        assertEquals(1000L, orderDBOpt.get().getOrderNumber());

        assertTrue(orderService.findById(999L).isEmpty());
        assertTrue(orderService.findById(0L).isEmpty());
        assertTrue(orderService.findById(-1L).isEmpty());
        assertTrue(orderService.findById(null).isEmpty());

        verify(orderRepo, times(2)).findById(anyLong());
    }

    @Test
    void findByOrderNumber() {
        Order orderDB = new Order(1L, 1000L, null, null, PaymentMethod.CREDIT_CARD);
        when(orderRepo.findByOrderNumber(1000L)).thenReturn(Optional.of(orderDB));

        assertTrue(orderService.findByOrderNumber(1000L).isPresent());
        assertTrue(orderService.findByOrderNumber(null).isEmpty());

        verify(orderRepo).findByOrderNumber(anyLong());
    }

    @Test
    void findByShoppingCart() {
        ShoppingCart shoppingCartDB1 = new ShoppingCart();
        ShoppingCart shoppingCartDB2 = new ShoppingCart();
        Order orderDB = new Order(1L, 1000L, shoppingCartDB1, null, PaymentMethod.CREDIT_CARD);
        when(orderRepo.findByShoppingCart(shoppingCartDB1)).thenReturn(Optional.of(orderDB));
        when(orderRepo.findByShoppingCart(shoppingCartDB2)).thenReturn(Optional.empty());

        assertTrue(orderService.findByShoppingCart(shoppingCartDB1).isPresent());
        assertTrue(orderService.findByShoppingCart(shoppingCartDB2).isEmpty());
        assertTrue(orderService.findByShoppingCart(null).isEmpty());
    }

    @Test
    void findByCustomer() {
        Customer customerDB = new Customer();
        ShoppingCart shoppingCartDB = new ShoppingCart();
        Order orderDB = new Order(1L, 1000L, shoppingCartDB, null, PaymentMethod.CREDIT_CARD);
        when(orderRepo.findByShoppingCartCustomer(customerDB)).thenReturn(Optional.of(orderDB));

        assertTrue(orderService.findByCustomer(customerDB).isPresent());
        assertTrue(orderService.findByCustomer(null).isEmpty());
    }

    @Test
    void save() {
        fail();
    }

    @Test
    void update() {
        fail();
    }

    @Test
    void deleteById() {
        fail();
    }
}