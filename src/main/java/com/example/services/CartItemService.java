package com.example.services;

import com.example.entities.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemService
{
    List<CartItem> findAll();

    boolean existsById(Long id);
    Optional<CartItem> findById(Long id);

    List<CartItem> findAllByProductId(Long id);
    List<CartItem> findAllByShoppinCart(Long id);

    List<CartItem> findAllByAmountIs(Long amount);
    List<CartItem> findAllByAmountGreaterThanEqual(Long minAmount);
    List<CartItem> findAllByAmountIsLessThanEqual(Long maxAmount);
    List<CartItem> findAllByAmountIsBetween(long minAmount, Long maxAmount);

    List<CartItem> findAllByPriceIs(double price);
    List<CartItem> findAllByPriceIsGreaterThanEqual(double price);
    List<CartItem> findAllByPriceIsLessThanEqual(double price);
    List<CartItem> findAllByPriceIsBetween(double minPrice, double maxPrice);


    CartItem save(CartItem cartItem);

    CartItem update(CartItem cartItem);

    CartItem delete(CartItem cartItem);

    //=====================  BUSINESS LOGIC  ========================

    void addAmountById(Long cartItem, Long amount);

    void removeAmountById(Long cartItem, Long amount);


}
