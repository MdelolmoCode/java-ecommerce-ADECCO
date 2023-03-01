package com.example.services;

import com.example.entities.*;
import com.example.repositories.CustomerRepository;
import com.example.repositories.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final UserEntityRepository userEntityRepository;

    private final CustomerRepository customerRepository;
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;
    private final OrderService orderService;
    private final UserService userService;


    //métodos

    @Override
    public List<Customer> findAll() { //tested
        log.info("Ejecutando método findAll() from CustomerService");
        return customerRepository.findAll();
    }


    @Override
    public Optional<Customer> findById(Long id) {
        log.info("Ejecutando método findById() from CustomerService {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return customerRepository.findById(id);
    }


    @Override
    public Optional<Customer> findBySurname(String surname) { //tested
        log.info("Ejecutando método findBySurname() from CustomerService {}", surname);
        return customerRepository.findBySurname(surname);
    }

    @Override
    public Optional<Customer> findByEmail(String email) { // tested
        log.info("Ejecutando método findByEmail() from CustomerService {}", email);
        return customerRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("existsByEmail {}", email);
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        log.info("Ejecutando método findByPhone() from CustomerService {}", phone);
        return customerRepository.findByPhone(phone);
    }


    @Override
    public Customer save(Customer customer) { // tested

        if (customer == null) {
            throw new IllegalArgumentException("Customer no puede ser null");
        }
        if (customer.getId() != null)
            update(customer);
        return customerRepository.save(customer);

    }

    @Override
    public void update(Customer customer) { //tested

        Customer customerFromDB = customerRepository.findById(customer.getId()).get();
        customerFromDB.setName(customer.getName());
        customerFromDB.setSurname(customer.getSurname());
        customerFromDB.setEmail(customer.getEmail());
        customerFromDB.setAddresses(customer.getAddresses());
        customerFromDB.setPhone(customer.getPhone());

        customerRepository.save(customerFromDB);
    }

    @Override
    public void deleteById(Long id) { // tested

        Optional<Customer> optionalCustomer = findById(id);

        if (optionalCustomer.isEmpty())
            return;

        Customer customer = optionalCustomer.get(); // customer by id

        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findByCustomer(customer); //carrito de customer
        Optional<Order> optionalOrder = orderService.findByCustomer(customer); // order de customer

        if (optionalOrder.isPresent()) {
            orderService.deleteById(optionalOrder.get().getId());
        }
        if (optionalShoppingCart.isPresent()) {

            List<CartItem> cartItems = optionalShoppingCart.get().getCartItems(); // lista items del carrito de customer
            System.out.println("CANTIDAD CART ITEMSSS");
            System.out.println(cartItems.size());

            if (!cartItems.isEmpty()) {
                for (CartItem c : cartItems) {

                    cartItemService.deleteById(c.getId());
                }
            }
            shoppingCartService.deleteById(optionalShoppingCart.get().getId());
        }
        userEntityRepository.deleteById(id);
        customerRepository.deleteById(id);

    }
}
