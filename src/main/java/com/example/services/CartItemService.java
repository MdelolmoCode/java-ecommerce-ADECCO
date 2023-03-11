package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.ShoppingCart;

import java.util.List;
import java.util.Optional;


public interface CartItemService
{
    //===============================  CRUD  =========================================

    List<CartItem> findAll();

    List<CartItem> findAllByOrderByShoppingCartId();

    boolean existsById(Long id);

    Optional<CartItem> findById(Long id);

    CartItem save(CartItem cartItem);

    void saveAll(List<CartItem> cartItemList);

    void deleteById(Long id);
    public void update(CartItem cartItem);

    //===============================  FILTERS  =========================================

    List<CartItem> findAllByProductId(Long id);

    List<CartItem> findAllByAmountIs(Long amount);
    List<CartItem> findAllByAmountGreaterThanEqual(Long minAmount);
    List<CartItem> findAllByAmountIsLessThanEqual(Long maxAmount);
    List<CartItem> findAllByAmountIsBetween(long minAmount, Long maxAmount);


    //=====================  BUSINESS LOGIC  ========================

    void addAmountById(Long cartItem, Long amount);

    void removeAmountById(Long cartItem, Long amount);

    List<CartItem> removeAllItems(List<CartItem> cartItemList);

    List<CartItem> findCartItemsByShoppingCartId (Long id);

    Long amountProductByCartItemList (List<CartItem> cartItemList);
}
