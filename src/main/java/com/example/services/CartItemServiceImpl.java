package com.example.services;

import com.example.entities.CartItem;
import com.example.repositories.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public boolean existsById(Long id)
    {
        log.info("ExistBy{}", id);
        if(id == null)
            return false;

        return cartItemRepo.existsById(id);
    }

    @Override
    public Optional<CartItem> findById(Long id)
    {
        log.info("FindBy{}",id);
        return cartItemRepo.findById(id);
    }

    @Override
    public List<CartItem> findByProductId(Long id)
    {
        log.info("FindByProduct{}", id);
        return cartItemRepo.findAllByProductId(id);
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
        log.info("FindByShoppingCart{}", id);
        return cartItemRepo.findAllByShoppingCart(id);
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
        log.info("FindByAmountIs{}", amount);
        return cartItemRepo.findAllByAmountIs(amount);
    }

    @Override
    public List<CartItem> findByAmountGreaterThanEqual(Long minAmount)
    {
        log.info("FindByAmountGreaterThanEqual{}", minAmount);
        if(minAmount == null || minAmount < 0)
            return null;
        return cartItemRepo.findAllByAmountGreaterThanEqual(minAmount);
    }

    @Override
    public List<CartItem> findByAmountIsLessThanEqual(Long maxAmount)
    {
        log.info("FindByAmountIsLessThanEqual{}", maxAmount);
        return cartItemRepo.findAllByAmountIsLessThanEqual(maxAmount);
    }

    @Override
    public List<CartItem> findByAmountIsBetween(long minAmount, Long maxAmount)
    {
        log.info("FindByAmountIsBetween {} and {}", minAmount, maxAmount);
        return cartItemRepo.findAllByAmountIsBetween(minAmount, maxAmount);
    }

    //================================================================================================

    @Override
    public List<CartItem> findByPriceIs(double price)
    {
        log.info("FindByPriceIs{}", price);
        return cartItemRepo.findAllByPriceIs(price);
    }

    @Override
    public List<CartItem> findByPriceIsGreaterThanEqual(double price)
    {
        log.info("FindByPriceIsGreaterThanEqual {}", price);
        return cartItemRepo.findAllByPriceIsGreaterThanEqual(price);
    }

    @Override
    public List<CartItem> findByPriceIsLessThanEqual(double price)
    {
        log.info("FindByPriceIsLessThanEqual {}", price);
        return cartItemRepo.findAllByPriceIsLessThanEqual(price);
    }

    @Override
    public List<CartItem> findByPriceIsBetween(double minPrice, double maxPrice)
    {
        log.info("FindByPriceIsBetween {} and {}", minPrice, maxPrice);
        return cartItemRepo.findAllByPriceIsBetween(minPrice, maxPrice);
    }

    //================================================================================================

    @Override
    public CartItem save(CartItem cartItem)
    {
        log.info("Saving {}", cartItem);
        if(cartItem != null)
        {
            cartItemRepo.save(cartItem);
            return cartItem;
        }
        return null;
    }

    @Override
    public void update(CartItem cartItem)
    {
        log.info("Update {}", cartItem);
        if(cartItem != null)
            cartItemRepo.save(cartItem);
    }

    @Override
    public void delete(CartItem cartItem)
    {
        log.info("Delete {}", cartItem);
        if(cartItem != null)
            cartItemRepo.delete(cartItem);
    }

    //============================  BUSINESS LOGIC  ===================================

    @Override
    public void addAmountById(Long id, Long amount)
    {
        log.info("AddAmountById {} {}", id, amount);

        if(id == null || amount <= 0)
            return;

        Optional<CartItem> optionalCartItem = cartItemRepo.findById(id);
        if(optionalCartItem.isPresent())
        {
            CartItem cartItem = optionalCartItem.get();
            Long amountPresent = cartItem.getAmount();
            cartItem.setAmount(amount + amountPresent);
        }
    }

    @Override
    public void removeAmount(Long id, Long amount)
    {
        log.info("RemoveAmountById {} {}", id, amount);

        if(id == null || amount >= 0)
            return;

        Optional<CartItem> optionalCartItem = cartItemRepo.findById(id);
        if(optionalCartItem.isPresent())
        {
            CartItem cartItem = optionalCartItem.get();
            Long amountPresent = cartItem.getAmount();
            if(amountPresent - amount > 0)
            {
                cartItem.setAmount(amountPresent - amount);
            }else{
                cartItem.setAmount(0L);
            }
        }
    }

}
