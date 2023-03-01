package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.ShoppingCart;
import com.example.repositories.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> findAll() {
        log.info("findAll");
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        log.info("findById {}", id);
        return shoppingCartRepository.findById(id);
    }

    @Override
    public Optional<ShoppingCart> findByCustomer(Customer customer) {
        log.info("findByCustomer {}", customer);
        return shoppingCartRepository.findByCustomer(customer);
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        try {
            this.shoppingCartRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting Product", e);
        }

    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        log.info("save ShoppingCart {}", shoppingCart);
        if(shoppingCart == null){
            throw new IllegalArgumentException("ShoppingCart can´t be null");
        }
        try {
            this.shoppingCartRepository.save(shoppingCart);
        } catch (Exception e) {
            log.error("Error al guardar ShoppingCart", e);
        }
        return shoppingCart;
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        log.info("update ShoppingCart {}", shoppingCart);
        if(shoppingCart == null){
            throw  new IllegalArgumentException("ShoppingCart can´t be null");
        }

        ShoppingCart shoppingCartFromDB = shoppingCartRepository.findById(shoppingCart.getId()).get();
        shoppingCartFromDB.setCustomer(shoppingCart.getCustomer());
        shoppingCartFromDB.setCartItems(shoppingCart.getCartItems());

        shoppingCartRepository.save(shoppingCartFromDB);
    }

    @Override
    public double calculateShoppingCartPrice(ShoppingCart shoppingCart) {
        log.info("Calcula precio carrito");

        List<CartItem> list = shoppingCart.getCartItems();

        double totalPrice = 0.0;

        for(CartItem c: list){
            totalPrice = totalPrice + (c.getProduct().getPrice() * c.getAmount());
        }
        return totalPrice;
    }




}
