package com.example.services;

import com.example.entities.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService
{
    List<CartItem> findAll();

    boolean existsById(Long id);
    Optional<CartItem> findById(Long id);

    List<CartItem> findByProductId(Long id);
    List<CartItem> findByShoppinCart(Long id);

    List<CartItem> findByAmountIs(Long amount);
    List<CartItem> findByAmountGreaterThanEqual(Long minAmount);
    List<CartItem> findByAmountIsLessThanEqual(Long maxAmount);
    List<CartItem> findByAmountIsBetween(long minAmount, Long maxAmount);

    List<CartItem> findByPriceIs(double price);
    List<CartItem> findByPriceIsGreaterThanEqual(double price);
    List<CartItem> findByPriceIsLessThanEqual(double price);
    List<CartItem> findByPriceIsBetween(double minPrice, double maxPrice);


    CartItem save(CartItem cartItem);

    void update(CartItem cartItem);

    void delete(CartItem cartItem);

    //=====================  BUSINESS LOGIC  ========================

    void addAmountById(Long cartItem, Long amount);

    void removeAmount(Long cartItem, Long amount);


}
