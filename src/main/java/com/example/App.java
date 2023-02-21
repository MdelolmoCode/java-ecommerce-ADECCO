package com.example;



import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.Product;
import com.example.entities.ShoppingCart;
import com.example.repositories.CartItemRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.ProductRepository;
import com.example.repositories.ShoppingCartRepository;
import com.example.services.CustomerService;
import com.example.services.ShoppingCartService;
import com.example.services.ShoppingCartServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;


@SpringBootApplication

public class App {


	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(App.class, args);

		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
		CustomerService customerService = context.getBean(CustomerService.class);

		ShoppingCartRepository shoppingCartRepository = context.getBean(ShoppingCartRepository.class);
		ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);

		ShoppingCart shoppingCart1 = new ShoppingCart();

		Customer customer1 = new Customer(null, "Pedro", "Rodriguez", "pedrorod@gmail.com", null, "665167676");
		Customer customer2 = new Customer(null, "Angela", "Diaz", "angeladiaz@gmail.com", null, "665453323");

		customerRepository.saveAll(List.of(customer1,customer2));

		ProductRepository productRepository = context.getBean(ProductRepository.class);

		Product product1 = new Product(null, "Cuchillo", "Cuchillo para cortar jamón", 2.04, 100L, true, null, null);
		Product product2 = new Product(null, "tenedor", "tenedor para ensalada", 1.40, 200L, true, null, null);

		productRepository.saveAll(List.of(product1,product2));

		CartItemRepository cartItemRepository = context.getBean(CartItemRepository.class);

		CartItem cartItem1 = new CartItem(null, shoppingCart1, product1, 3L);
		CartItem cartItem2 = new CartItem(null, shoppingCart1, product2, 3L);

		cartItemRepository.saveAll(List.of(cartItem1,cartItem2));

		List<CartItem> lista = cartItemRepository.findAll();

		shoppingCart1.setCustomer(customer1);
		shoppingCart1.setCartItems(lista);

	shoppingCartService.update(shoppingCart1);

	System.out.println(shoppingCartRepository.findAll());

		//ShoppingCart sp1 = shoppingCartRepository.findByCustomer(); devuelve un optional

	double price = shoppingCartService.calculateShoppingCartPrice(shoppingCart1);

	System.out.println(price);





	}
}
