package com.example;

import com.example.entities.*;
import com.example.entities.enums.PaymentMethod;
import com.example.repositories.*;
import com.example.services.*;
import com.example.entities.Address;
import com.example.entities.Category;
import com.example.entities.Manufacturer;
import com.example.entities.Order;
import com.example.entities.Product;
import com.example.repositories.AddressRepository;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import com.example.services.CategoryService;
import com.example.services.ManufacturerService;
import com.example.services.ProductService;
import com.example.services.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

	static ApplicationContext context;
	public static void main(String[] args) {
		// Inicializa spring
		context = SpringApplication.run(App.class, args);

		// REPOSITORIOS

		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
		CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
		ManufacturerRepository manufacturerRepo = context.getBean(ManufacturerRepository.class);
		ProductRepository productRepo = context.getBean(ProductRepository.class);
		ShoppingCartRepository shoppingCartRepo = context.getBean(ShoppingCartRepository.class);
		CartItemRepository cartItemRepo = context.getBean(CartItemRepository.class);
		OrderRepository orderRepo = context.getBean(OrderRepository.class);

		// SERVICIOS

		AddressService addressService = context.getBean(AddressService.class);
		CategoryService categoryService = context.getBean(CategoryService.class);
		CustomerService customerService = context.getBean(CustomerService.class);
		ManufacturerService manufacturerService = context.getBean(ManufacturerService.class);
		ProductService productService = context.getBean(ProductService.class);
		ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
		CartItemService cartItemService = context.getBean(CartItemService.class);
		OrderService orderService = context.getBean(OrderService.class);




	}

}
