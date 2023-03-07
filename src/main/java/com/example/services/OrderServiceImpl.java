package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.ShoppingCart;
import com.example.exception.EntityDeleteException;
import com.example.exception.EntitySavingException;
import com.example.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private static long currentOrderNumber = 1;

    @Override
    public List<Order> findAll() {
        log.info("findAll");
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> findByOrderNumber(Long orderNumber) {
        log.info("findByOrderNumber {}", orderNumber);
        if (orderNumber == null)
            return Optional.empty();
        return orderRepository.findByOrderNumber(orderNumber);
    }

    @Override
    public List<Order> findAllByAddressCity(String city) {
        log.info("findAllByAddressCity {}", city);
        if (!StringUtils.hasLength(city))
            return new ArrayList<>();
        return orderRepository.findAllByAddressCity(city);
    }

    @Override
    public Optional<Order> findByCustomer(Customer customer) {
        log.info("findByCustomer {}", customer);
        if (customer == null)
            return Optional.empty();
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public double calculateTotalPrice(List<CartItem> cartItems) {
        log.info("calculateTotalPrice {}", cartItems);
        if (cartItems == null)
            return 0;

        return cartItems.stream().mapToDouble(CartItem::getPrice).sum();
    }

    @Override
    public void emptyCart(ShoppingCart shoppingCart) {
        shoppingCart.getCartItems().forEach(cartItem -> cartItem.setShoppingCart(null));
        shoppingCart.getCartItems().clear();
    }

    @Override
    public long getAvailableOrderNumber() {
        return currentOrderNumber++;
    }

    @Override
    public Order save(Order order) {
        log.info("save {}", order);
        if(order == null)
            throw new IllegalArgumentException("Pedido nulo.");

        if(order.getId() != null)
            return update(order);

        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            log.error("Error al guardar pedido.", e);
        }

        throw new EntitySavingException("Error al guardar pedido.");
    }

    @Override
    public Order update(Order order) {
        log.info("update {}", order);
        if(order == null)
            throw new IllegalArgumentException("Pedido nulo.");

        if(order.getId() == null)
            throw new IllegalArgumentException("Pedido con ID nulo.");

        Optional<Order> orderOpt = orderRepository.findById(order.getId());
        if (orderOpt.isEmpty())
            throw new EntityNotFoundException("No existe un pedido con ese ID.");

        Order orderFromDB = orderOpt.get();
        orderFromDB.setOrderNumber(order.getOrderNumber());
        orderFromDB.setAddress(order.getAddress());
        orderFromDB.setPaymentMethod(order.getPaymentMethod());
        orderFromDB.setCustomer(order.getCustomer());
        orderFromDB.setCartItems(order.getCartItems());

        try {
            return orderRepository.save(orderFromDB);
        } catch (Exception e) {
            log.error("Error al guardar pedido.", e);
        }

        throw new EntitySavingException("Error al guardar pedido.");

    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        if (id == null || id <= 0)
            throw new IllegalArgumentException("ID inválido");
        try {
            orderRepository.deleteById(id);
            return;
        } catch (Exception e) {
            log.error("Error al borrar pedido.", e);
        }

        throw new EntityDeleteException("Error al borrar pedido.");
    }
}
