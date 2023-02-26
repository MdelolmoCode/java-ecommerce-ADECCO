package com.example.repositories;

import com.example.entities.CartItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CartItemRepoTest
{
    @Autowired
    private CartItemRepository cartItemRepo;

    @Sql("DataInsertions.sql")
    @DisplayName("Buscar todos")
    @Test
    void findAllTest()
    {
        List<CartItem> cartItemList = cartItemRepo.findAll();

        assertNotNull(cartItemList);
        assertEquals(24, cartItemList.size());

        CartItem cartItem1 = cartItemList.get(0);
        assertEquals(10.99, cartItem1.getPrice());
        assertEquals(1, cartItem1.getAmount());
    }

    @Sql("DataInsertions.sql")
    @DisplayName("Buscar por id")
    @Test
    void findByIdTest()
    {
        Long id = cartItemRepo.findAll().get(0).getId();
        assertTrue(cartItemRepo.findById(id).isPresent() );
    }

    @Sql("DataInsertions.sql")
    @DisplayName("GetPrice")
    @Test
    void getPriceTest()
    {
        CartItem cartItem1 = cartItemRepo.findById(1L).get();
        assertEquals(10.99, cartItem1.getPrice());
    }

}
