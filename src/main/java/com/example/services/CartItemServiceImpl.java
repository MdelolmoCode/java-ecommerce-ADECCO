package com.example.services;

import com.example.entities.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartItemServiceImpl implements CartItemService
{
    @Override
    public List<CartItem> findAll()
    {
        return null;
    }

    @Override
    public boolean existsById(Long id)
    {
        return false;
    }

    @Override
    public Optional<CartItem> findById(Long id)
    {
        return Optional.empty();
    }

    @Override
    public List<CartItem> findByProductId(Long id)
    {
        return null;
    }

    @Override
    public List<CartItem> findByShoppinCart(Long id)
    {
        return null;
    }

    @Override
    public List<CartItem> findByAmountIs(Long amount)
    {
        return null;
    }

    @Override
    public List<CartItem> findByAmountGreaterThanEqual(Long minAmount)
    {
        return null;
    }

    @Override
    public List<CartItem> findByAmountIsLessThanEqual(Long maxAmount)
    {
        return null;
    }

    @Override
    public List<CartItem> findByAmountIsBetween(long minAmount, Long maxAmount)
    {
        return null;
    }

    @Override
    public List<CartItem> findByPriceIs(double price)
    {
        return null;
    }

    @Override
    public List<CartItem> findByPriceIsGreaterThanEqual(double price)
    {
        return null;
    }

    @Override
    public List<CartItem> findByPriceIsLessThanEqual(double price)
    {
        return null;
    }

    @Override
    public List<CartItem> findByPriceIsBetween(double minPrice, double maxPrice)
    {
        return null;
    }


}
