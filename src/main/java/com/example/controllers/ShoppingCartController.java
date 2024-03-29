package com.example.controllers;

import com.example.entities.Customer;
import com.example.entities.ShoppingCart;
import com.example.services.CartItemService;
import com.example.services.CustomerService;
import com.example.services.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CustomerService customerService;
    private final CartItemService cartItemService;

    @GetMapping("/shoppingCarts")
    public String findAll(Model model) {
        model.addAttribute("shoppingcarts", shoppingCartService.findAll());
        return "shoppingCart/shoppingcarts-list"; //
    }

    @GetMapping("/shoppingCarts/{id}")
    public String findById(Model model, @PathVariable Long id) {

        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findById(id);
        if(optionalShoppingCart.isPresent()){
            model.addAttribute("shoppincartById", optionalShoppingCart.get());
            model.addAttribute("customer", optionalShoppingCart.get().getCustomer());
            model.addAttribute("cartItems", optionalShoppingCart.get().getCartItems());
            model.addAttribute("amountProducts", cartItemService.amountProductByCartItemList(optionalShoppingCart.get().getCartItems()));
            model.addAttribute("totalShoppingCartCost", shoppingCartService.calculateShoppingCartPrice(optionalShoppingCart.get()));

        }else{
            model.addAttribute("error", "Not Found");
        }
        return "shoppingCart/shoppingcarts-detail";
    }

    @GetMapping("shoppingCarts/byCustomer/{customerId}")
    public String findByCustomerId(Model model, @PathVariable Long customerId) {
        Optional<Customer> customerOpt = customerService.findById(customerId);
        if (customerOpt.isEmpty())
            return "redirect:/shoppingCarts";

        Optional<ShoppingCart> shoppingCartOpt = shoppingCartService.findByCustomer(customerOpt.get());
        if (shoppingCartOpt.isEmpty())
            return "redirect:/shoppingCarts";

        Long shoppingCartId = shoppingCartOpt.get().getId();
        return "redirect:/shoppingCarts/" + shoppingCartId;
    }

    @GetMapping("/shoppingCarts/{id}/emptyShoppingCart")
    public String emptyShoppingCart(Model model, @PathVariable Long id) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findById(id);
        if(optionalShoppingCart.isPresent()){

            model.addAttribute("removeItemsFromShoppingCart", cartItemService.removeAllItems(optionalShoppingCart.get().getCartItems()));

        }

        return "redirect:/shoppingCarts/{id}";
    }

    @GetMapping("/shoppingCarts/{id}/{idItem}/delete")
    public String deleteCartItem(@PathVariable Long id, @PathVariable Long idItem){

        cartItemService.deleteById(idItem);

        return "redirect:/shoppingCarts/{id}";
    }



    @GetMapping("/shoppingCarts/{id}/{idItem}/deleteOne")
    public String deleteOneProductFromItem(@PathVariable Long id, @PathVariable Long idItem){
        cartItemService.removeAmountById(idItem, 1L);
        return "redirect:/shoppingCarts/{id}";
    }

    @GetMapping("/shoppingCarts/{id}/{idItem}/addOne")
    public String addOneProductFromItem(@PathVariable Long id, @PathVariable Long idItem){
        cartItemService.addAmountById(idItem, 1L);
        return "redirect:/shoppingCarts/{id}";
    }

}
