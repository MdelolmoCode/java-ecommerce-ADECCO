package com.example.services;

import com.example.entities.CartItem;
import com.example.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService
{
    private final CartItemRepository cartItemRepo;

    @Override
    public List<CartItem> findAll()
    {
        return cartItemRepo.findAll();
    }

    @Override
    public boolean existsById(Long id)
    {
        if(id == null)
            return false;

        return cartItemRepo.existsById(id);
    }

    @Override
    public Optional<CartItem> findById(Long id)
    {
        return cartItemRepo.findById(id);
    }

    @Override
    public List<CartItem> findByProductId(Long id)
    {
        return cartItemRepo.findByProductId(id);
//        List<CartItem> cartItemsByProcduct = new ArrayList<>();
//
//        for (CartItem c : cartItemRepo.findAll()) {
//            if(Objects.equals(c.getProduct().getId(), id))
//                cartItemsByProcduct.add(c);
//        }
//
//        return cartItemsByProcduct;
    }

    @Override
    public List<CartItem> findByShoppinCart(Long id)
    {
        return cartItemRepo.findByShoppinCart(id);
//        List<CartItem> cartItemsByShoppingCart = new ArrayList<>();
//
//        for (CartItem c : cartItemRepo.findAll())
//        {
//            if(c.getShoppingCart().getId() == id)
//                cartItemsByShoppingCart.add(c);
//        }
//
//        return cartItemsByShoppingCart;
    }

    //================================================================================================

    @Override
    public List<CartItem> findByAmountIs(Long amount)
    {
        return cartItemRepo.findByAmountIs(amount);
    }

    @Override
    public List<CartItem> findByAmountGreaterThanEqual(Long minAmount)
    {
        if(minAmount == null || minAmount < 0)
            return null;
        return cartItemRepo.findByAmountGreaterThanEqual(minAmount);
    }

    @Override
    public List<CartItem> findByAmountIsLessThanEqual(Long maxAmount)
    {
        return cartItemRepo.findByAmountIsLessThanEqual(maxAmount);
    }

    @Override
    public List<CartItem> findByAmountIsBetween(long minAmount, Long maxAmount)
    {
        return cartItemRepo.findByAmountIsBetween(minAmount, maxAmount);
    }

    //================================================================================================

    @Override
    public List<CartItem> findByPriceIs(double price)
    {
        return cartItemRepo.findByPriceIs(price);
    }

    @Override
    public List<CartItem> findByPriceIsGreaterThanEqual(double price)
    {
        return cartItemRepo.findByPriceIsGreaterThanEqual(price);
    }

    @Override
    public List<CartItem> findByPriceIsLessThanEqual(double price)
    {
        return cartItemRepo.findByPriceIsLessThanEqual(price);
    }

    @Override
    public List<CartItem> findByPriceIsBetween(double minPrice, double maxPrice)
    {
        return cartItemRepo.findByPriceIsBetween(minPrice, maxPrice);
    }


}
