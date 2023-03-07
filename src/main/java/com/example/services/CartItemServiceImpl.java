package com.example.services;

import com.example.entities.CartItem;
import com.example.entities.ShoppingCart;
import com.example.exception.EntitySavingException;
import com.example.repositories.CartItemRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private  final  ShoppingCartService shoppingCartService;

    @Override
    public List<CartItem> findAll()
    {
        log.info("findAll");
        return cartItemRepo.findAll();
    }

    @Override
    public List<CartItem> findAllByOrderByShoppingCartId()
    {
        return cartItemRepo.findAllByOrderByShoppingCartId();
    }

    @Override
    public boolean existsById(Long id)
    {
        log.info("ExistBy {}", id);

        if(! idAmountPriceValido(id))
            return false;

        return cartItemRepo.existsById(id);
    }

    @Override
    public Optional<CartItem> findById(Long id)
    {
        log.info("FindBy{}",id);

        if(! idAmountPriceValido(id))
            return Optional.empty();

        return cartItemRepo.findById(id);
    }

    @Override
    public List<CartItem> findAllByProductId(Long id)
    {
        log.info("FindByProduct{}", id);

        if(! idAmountPriceValido(id))
            return null;

        return cartItemRepo.findAllByProductId(id);
    }


    //================================================================================================

    @Override
    public List<CartItem> findAllByAmountIs(Long amount)
    {
        log.info("FindByAmountIs{}", amount);

        if(! idAmountPriceValido(amount))
            return null;

        return cartItemRepo.findAllByAmountIs(amount);
    }

    @Override
    public List<CartItem> findAllByAmountGreaterThanEqual(Long minAmount)
    {
        log.info("FindByAmountGreaterThanEqual{}", minAmount);

        if(idAmountPriceValido(minAmount))
            return null;

        return cartItemRepo.findAllByAmountGreaterThanEqual(minAmount);
    }

    @Override
    public List<CartItem> findAllByAmountIsLessThanEqual(Long maxAmount)
    {
        log.info("FindByAmountIsLessThanEqual{}", maxAmount);

        if(idAmountPriceValido(maxAmount))
            return null;

        return cartItemRepo.findAllByAmountIsLessThanEqual(maxAmount);
    }

    @Override
    public List<CartItem> findAllByAmountIsBetween(long minAmount, Long maxAmount)
    {
        log.info("FindByAmountIsBetween {} and {}", minAmount, maxAmount);

        if(! idAmountPriceValido(minAmount) || ! idAmountPriceValido(maxAmount))
            return null;

        return cartItemRepo.findAllByAmountIsBetween(minAmount, maxAmount);
    }

    //================================================================================================

//    @Override
//    public List<CartItem> findAllByPriceIs(double price)
//    {
//        log.info("FindByPriceIs{}", price);
//        return cartItemRepo.findAllByPriceIs(price);
//    }
//
//    @Override
//    public List<CartItem> findAllByPriceIsGreaterThanEqual(double price)
//    {
//        log.info("FindByPriceIsGreaterThanEqual {}", price);
//        return cartItemRepo.findAllByPriceIsGreaterThanEqual(price);
//    }
//
//    @Override
//    public List<CartItem> findAllByPriceIsLessThanEqual(double price)
//    {
//        log.info("FindByPriceIsLessThanEqual {}", price);
//        return cartItemRepo.findAllByPriceIsLessThanEqual(price);
//    }
//
//    @Override
//    public List<CartItem> findAllByPriceIsBetween(double minPrice, double maxPrice)
//    {
//        log.info("FindByPriceIsBetween {} and {}", minPrice, maxPrice);
//        return cartItemRepo.findAllByPriceIsBetween(minPrice, maxPrice);
//    }

    //================================================================================================

    /*
    Guarda el cartItem pasado como parametro si no es nulo y
    si su clave SI es nula (el repositorio la asigna).
    En caso de exito devuelve el cartItem.
    Null en caso contrario.
     */
    @Override
    public CartItem save(CartItem cartItem)
    {
        log.info("Guardando cartItem {}", cartItem);


        if(cartItem == null) {
            log.info("Error: cartItem nulo");
            throw new IllegalArgumentException("Error: cartItem nulo");
        }

        if(cartItem.getId() != null){
            log.info("Error: la clave debe ser nula");
            throw new IllegalArgumentException("La clave debe ser nula");
        }

        try {
            return cartItemRepo.save(cartItem);
        }catch (Exception e){
            log.info("Error al guardar cartItem" + e.getMessage());
        }

        throw new EntitySavingException("Error al guardar cartItem");
    }

    public void saveAll(List<CartItem> cartItemList)
    {
        for (CartItem c : cartItemList)
        {
            try {
                save(c);
            }catch (Exception e){
                log.info("Error al guardar lista de cartItems: " + e.getMessage());
            }
        }
    }

    /*
    Si el cartItem parametro no es nulo y  existe en la BB.DD,
    sustituye los atributos del almacenado por los del parametro.
     */
    @Override
    public CartItem update(CartItem cartItem)
    {
        log.info("Updating {}", cartItem);

        if(cartItem == null){
            log.info("Error: cartItem nulo");
            throw new IllegalArgumentException("Error: cartItem nulo");
        }

        Optional<CartItem> cartItemToUpdateOptional = findById(cartItem.getId());
        if(cartItemToUpdateOptional.isEmpty()){
            log.info("Error: cartItem inexistente");
            throw new EntityNotFoundException("Error: cartItem inexistente");
        }else {
            cartItemRepo.save(cartItem);
//            CartItem cartItemAlmacenada = cartItemToUpdateOptional.get();
//            cartItemAlmacenada.setAmount(cartItem.getAmount());
//            cartItemAlmacenada.setPrice(cartItem.getPrice());
//            cartItemAlmacenada.setProduct(cartItem.getProduct());
        }

        return cartItem;
    }

    /*
    Si el cartItem parametro no es nulo y  existe en la BB.DD, lo borra.
     */
    @Override
    public void deleteById(Long id)
    {
        log.info("Borrando cartItem {}", id);

        if(id == null) {
            log.info("Error: id nulo");
            throw new IllegalArgumentException("Error: id nulo");
        }


        cartItemRepo.deleteById(id);
    }

    //============================  BUSINESS LOGIC  ===================================

    @Override
    public void addAmountById(Long id, Long amount)
    {
        log.info("AddAmountById {} {}", id, amount);

        if(! idAmountPriceValido(id) || ! idAmountPriceValido(amount))
            return;

        Optional<CartItem> optionalCartItem = cartItemRepo.findById(id);
        if(optionalCartItem.isEmpty())
        {
            log.info("Error: cartItem no encontrado");
        }else {
            CartItem cartItem = optionalCartItem.get();
            Long amountPresent = cartItem.getAmount();
            cartItem.setAmount(amountPresent + amount);
        }
    }

    @Override
    public void removeAmountById(Long id, Long amount)
    {
        log.info("AddAmountById {} {}", id, amount);

        if(! idAmountPriceValido(id) || ! idAmountPriceValido(amount))
            return;

        Optional<CartItem> optionalCartItem = cartItemRepo.findById(id);
        if(optionalCartItem.isEmpty())
        {
            log.info("Error: cartItem no encontrado");

        }else {
            CartItem cartItem = optionalCartItem.get();
            Long amountPresent = cartItem.getAmount();
            cartItem.setAmount(amountPresent - amount);
        }
    }

    @Override
    public List<CartItem> removeAllItems(List<CartItem> cartItemList) {

        for(CartItem c: cartItemList){

            deleteById(c.getId());
        }
        return cartItemList;
    }

    @Override
    public List<CartItem> findCartItemsByShoppingCartId(Long id) {

        ShoppingCart shoppingCart = shoppingCartService.findById(id).get();

        return shoppingCart.getCartItems();
    }

    @Override
    public Long amountProductByCartItemList(List<CartItem> cartItemList) {

        Long amountProducts = 0L;
        for(CartItem c: cartItemList){

            amountProducts = c.getAmount() + amountProducts;
        }
        return amountProducts;
    }

    //============================  UTILITIES  ===================================

    private boolean idAmountPriceValido(Long toCheck)
    {
        if(toCheck == null || toCheck <= 0){
            log.info("Error: parametro nulo o no valido");
            return false;
        }

        return true;
    }

}
