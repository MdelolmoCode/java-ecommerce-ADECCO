package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.ShoppingCart;
import com.example.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService
{
    private final CartItemRepository cartItemRepo;

    @Override
    public List<CartItem> findAll()
    {
        log.info("findAll");
        return cartItemRepo.findAll();
    }

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        log.info("findByShoppingCart");
        List<CartItem> listFromDB = cartItemRepo.findAll();

        List<CartItem> aux = new ArrayList<>();

        for (CartItem cartItem : listFromDB) {
            if(cartItem.getShoppingCart().getId().equals(shoppingCart.getId())) {
                aux.add(cartItem);
            }
        }
        return aux;
    }
}
