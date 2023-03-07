package com.example.services;

import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.enums.PaymentMethod;
import com.example.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
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
                new Order(1L, 1000L, null, null, null, PaymentMethod.CREDIT_CARD),
                new Order(2L, 2000L, null, null, null, PaymentMethod.CREDIT_CARD),
                new Order(3L, 3000L, null, null, null, PaymentMethod.DEBIT_CARD)
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
        Order orderDB = new Order(1L, 1000L, null, null, null, PaymentMethod.CREDIT_CARD);
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
        Order orderDB = new Order(1L, 1000L, null, null, null, PaymentMethod.CREDIT_CARD);
        when(orderRepo.findByOrderNumber(1000L)).thenReturn(Optional.of(orderDB));

        assertTrue(orderService.findByOrderNumber(1000L).isPresent());
        assertTrue(orderService.findByOrderNumber(null).isEmpty());

        verify(orderRepo).findByOrderNumber(anyLong());
    }

    @Test
    void findByCustomer() {
        Customer customerDB = new Customer();
        Order orderDB = new Order(1L, 1000L, customerDB, null, null, PaymentMethod.CREDIT_CARD);
        when(orderRepo.findByCustomer(customerDB)).thenReturn(Optional.of(orderDB));

        assertTrue(orderService.findByCustomer(customerDB).isPresent());
        assertTrue(orderService.findByCustomer(null).isEmpty());
    }

    @Test
    void save() {
        Order orderInput = new Order(null, 1000L, null, null, null, PaymentMethod.PAYPAL);
        Order orderOutput = new Order(1L, 1000L, null, null, null, PaymentMethod.PAYPAL);
        when(orderRepo.save(orderInput)).thenReturn(orderOutput);

        Order orderDB = orderService.save(orderInput);

        assertNotNull(orderDB);
        assertEquals(1L, orderDB.getId());

        verify(orderRepo).save(orderInput);
        verify(orderRepo, never()).findById(any());
    }

    @Test
    void saveWithId() {
        Order orderDB = new Order(1L, 1000L, null, null, null, PaymentMethod.PAYPAL);
        when(orderRepo.save(orderDB)).thenReturn(orderDB);
        when(orderRepo.findById(1L)).thenReturn(Optional.of(orderDB));

        Order orderReturned = orderService.save(orderDB);

        assertNotNull(orderReturned);
        assertEquals(1L, orderReturned.getId());

        verify(orderRepo).save(orderDB);
        verify(orderRepo).findById(1L);
    }

    @Test
    void saveNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.save(null));
    }

    @Test
    void update() {
        Order orderOld = new Order(1L, 1000L, null, null, null, PaymentMethod.PAYPAL);
        Order orderNew = new Order(1L, 2000L, null, null, null, PaymentMethod.PAYPAL);
        when(orderRepo.findById(1L)).thenReturn(Optional.of(orderOld));
        when(orderRepo.save(any())).thenReturn(orderNew);

        Order orderDB = orderService.update(orderNew);

        assertNotNull(orderDB);
        assertEquals(1L, orderDB.getId());
        assertEquals(2000L, orderDB.getOrderNumber());
    }

    @Test
    void updateNull() {
        assertThrows(IllegalArgumentException.class, () -> orderService.update(null));
    }

    @Test
    void updateIdNull() {
        Order order = new Order(null, 1000L, null, null, null, PaymentMethod.PAYPAL);
        assertThrows(IllegalArgumentException.class, () -> orderService.update(order));
    }

    @Test
    void updateNotInDB() {
        Order order = new Order(1L, 2000L, null, null, null, PaymentMethod.PAYPAL);
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.update(order));
    }

    @Test
    void deleteById() {
        orderService.deleteById(1L);

        verify(orderRepo).deleteById(1L);
    }

    @Test
    void deleteByIdNull() {
        doThrow(IllegalArgumentException.class).when(orderRepo).deleteById(null);

        assertThrows(IllegalArgumentException.class, () -> orderService.deleteById(null));
    }
}