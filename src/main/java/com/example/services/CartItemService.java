package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface CartItemService
{
    List<CartItem> findAll();

    List<CartItem> findByShoppingCart (ShoppingCart shoppingCart);


}
