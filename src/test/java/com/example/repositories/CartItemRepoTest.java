package com.example.repositories;

import com.example.entities.CartItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartItemRepoTest
{
    @Autowired
    private CartItemRepository cartItemRepo;

    @Sql("CartItem.sql")
    @DisplayName("Buscar todos")
    @Test
    void findAllTest()
    {
        List<CartItem> cartItemList = cartItemRepo.findAll();

        assertNotNull(cartItemList);
        assertEquals(9, cartItemList.size());

        CartItem cartItem1 = cartItemList.get(0);
        cartItem1.getPrice()

    }

    @Sql("CartItem.sql")
    @DisplayName("Buscar por id")
    @Test
    void findByIdTest()
    {
        Optional<CartItem> opt
    }

    @Sql("CartItem.sql")
    @DisplayName("GetPrice")
    @Test
    void getPriceTest()
    {
        CartItem cartItem1 = cartItemRepo.findById(0L).get();
        assertEquals(1.0, cartItem1.getPrice());
    }

}
