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

		// AddressService addressService = context.getBean(AddressService.class);
		CategoryService categoryService = context.getBean(CategoryService.class);
		ManufacturerService manufacturerService = context.getBean(ManufacturerService.class);
		ProductService productService = context.getBean(ProductService.class);

		createDemoData();

		// categories
		CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
		Category category1 = new Category("arte",false);
		Category category2 = new Category("bricolaje",false);
		Category category3 = new Category("lencería",true);
		Category category4 = new Category("armas de fuego",true);
		Category category5 = new Category("armas blancas",true);
		Category category6 = new Category("armas",true);
		Category category7 = new Category("ropa",false);
		Category category8 = new Category("bisutería",false);

		categoryRepo.saveAll(List.of(category1, category2, category3,category4,category5,category6,category7,category8));

		// address
		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		Address address1 = new Address(1L, "street1","name1","city1","state1","country1","zipcode1");
		Address address2 = new Address(2L, "street2","name2","city2","state2","country1","zipcode2");
		// addressRepo.saveAll(address1, address2);
		addressRepo.save(address1);
		addressRepo.save(address2);

		// manufacturer
		ManufacturerRepository manufacturerRepo = context.getBean(ManufacturerRepository.class);
		Manufacturer manufacturer1 = new Manufacturer(null,"cif1","name1",address1,"1234phone");
		Manufacturer manufacturer2 = new Manufacturer(null,"cif2","name2",address2,"5678phone");
		// Manufacturer manufacturer3 = new Manufacturer(null,"cif3","name3",address2,"9991phone");
		// manufacturerRepo.saveAll(List.of(manufacturer1, manufacturer2, manufacturer3));
		manufacturerRepo.save(manufacturer1);
		manufacturerRepo.save(manufacturer2);
		// manufacturerRepo.save(manufacturer3);


		// product
		// productos sin ID
		ProductRepository productRepo = context.getBean(ProductRepository.class);
		Product product1 = new Product(null,"pincel", "desc pincel", 10.99, 10L,true,
				new ArrayList<>(List.of(category1,category2)),manufacturer1);
		Product product2 = new Product(null,"acuarelas", "desc acuarelas", 20.99, 20L,true,
				new ArrayList<>(List.of(category1)),manufacturer1);
		Product product3 = new Product(null,"pincel de acuarelas", "desc pincel de acuarelas", 30.99, 30L,true,
				new ArrayList<>(List.of(category1)),manufacturer1);
		Product product4 = new Product(null,"ropa de noche blanca", "desc ropa de noche blanco", 40.99, 40L,true,
				new ArrayList<>(List.of(category3,category7)),manufacturer1);
		Product product5 = new Product(null,"collar de noche", "desc collar de noche", 50.99, 50L,true,
				new ArrayList<>(List.of(category3,category8)),manufacturer1);
		Product product6 = new Product(null,"rodillo de pintura", "desc rodillo de pintura", 60.99, 60L,true,
				new ArrayList<>(List.of(category2)),manufacturer2);
		Product product7 = new Product(null,"escopeta", "desc escopeta", 70.99, 70L,false,
				new ArrayList<>(List.of(category4,category6)),manufacturer2);
		Product product8 = new Product(null,"pistola", "desc pistola", 80.99, 0L,false,
				new ArrayList<>(List.of(category4,category6)),manufacturer2);
		Product product9 = new Product(null,"cuchillo", "desc cuchillo", 90.99, 90L,true,
				new ArrayList<>(List.of(category5,category6)),manufacturer2);
		Product product10 = new Product(null,"machete", "desc machete", 100.99, 100L,true,
				new ArrayList<>(List.of(category5,category6)),manufacturer2);


		productRepo.saveAll(List.of(product1,product2,product3,product4,product5,
				product6,product7,product8, product9, product10));

		System.out.println("------============ AQUI EMPIEZA ============-------------");
        System.out.println("------**************************************-------------");
        System.out.println("------======================================-------------");

		testOrder(context);
		testManufacturer(context);
	}

	private static void createDemoData() {
/*



*/
/*
		// product
		// productos sin ID, Category ni Manufacturer
		ProductRepository productRepo = context.getBean(ProductRepository.class);
		Product product1 = new Product("pincel", "desc pincel", 10.99, 10L,true);
		Product product2 = new Product("acuarelas", "desc acuarelas", 20.99, 20L,true);
		Product product3 = new Product("pincel de acuarelas", "desc pincel de acuarelas", 30.99, 30L,true);
		Product product4 = new Product("ropa de noche blanca", "desc ropa de noche blanco", 40.99, 40L,true);
		Product product5 = new Product("collar de noche", "desc collar de noche", 50.99, 50L,true);
		Product product6 = new Product("rodillo de pintura", "desc rodillo de pintura", 60.99, 60L,true);
		Product product7 = new Product("escopeta", "desc escopeta", 70.99, 70L,false);
		Product product8 = new Product("pistola", "desc pistola", 80.99, 0L,false);
		Product product9 = new Product("cuchillo", "desc cuchillo", 90.99, 90L,true);
		Product product10 = new Product("machete", "desc machete", 100.99, 100L,true);*/

/*

		productRepo.saveAll(List.of(product1,product2,product3,product4,product5,
				product6,product7,product8, product9, product10));
*/



		// testOrder(context);
	}

	private static void testOrder(ApplicationContext context) {
		System.out.println("===== Test Order =====");

		Address address1 = new Address(null, "street O1","name O1","city O1","state O1","country O1","zipcode O1");
		Address address2 = new Address(null, "street O2","name O2","city O2","state O2","country O1","zipcode O2");
		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		addressRepo.saveAll(List.of(address1, address2));

		Customer customer1 = new Customer(null, "Name1", "Surname1", "email@email", new ArrayList<>(), "12345678");
		CustomerRepository customerRepo = context.getBean(CustomerRepository.class);
		customerRepo.save(customer1);

		ShoppingCart shoppingCart1 = new ShoppingCart(null, customer1, 10.0, null);
		ShoppingCartRepository shoppingCartRepo = context.getBean(ShoppingCartRepository.class);
		shoppingCartRepo.save(shoppingCart1);

		OrderService orderService = context.getBean(OrderService.class);
		Order order1 = new Order(null, 1000L, null, address1);
		Order order2 = new Order(null, 2000L, null, address2);
		Order order3 = new Order(null, 3000L, null, address2);
		orderService.save(order1);
		orderService.save(order2);
		orderService.save(order3);
		orderService.findAll().forEach(System.out::println);

		orderService.findByAddressCity("city2").forEach(System.out::println);

		orderService.deleteById(2L);
		orderService.findAll().forEach(System.out::println);

		order3.setOrderNumber(4000L);
		orderService.update(order3);
		System.out.println(orderService.findById(3L).get().getOrderNumber());

		System.out.println(orderService.findByShoppingCart(shoppingCart1));
		System.out.println(orderService.findByCustomer(customer1));
	}

	private static void testManufacturer(ApplicationContext context) {
		System.out.println("===== Test Manufacturer =====");

		Address address1 = new Address(null, "street M1","name M1","city M1","state M1","country M1","zipcode M1");
		Address address2 = new Address(null, "street M2","name M2","city M2","state M2","country M2","zipcode M2");
		Address address3 = new Address(null, "street M3","name M3","city M1","state M3","country M3","zipcode M3");
		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		addressRepo.saveAll(List.of(address1, address2, address3));

		ManufacturerService manufacturerService = context.getBean(ManufacturerService.class);
		Manufacturer manufacturer1 = new Manufacturer(null, "1111", "Manu1", address1, "12345678");
		Manufacturer manufacturer2 = new Manufacturer(null, "2222", "Manu2", address2, "22222222");
		Manufacturer manufacturer3 = new Manufacturer(null, "3333", "Manu3", address3, "33333333");
		manufacturerService.save(manufacturer1);
		manufacturerService.save(manufacturer2);
		manufacturerService.save(manufacturer3);
		manufacturerService.findAll().forEach(System.out::println);

		System.out.println(manufacturerService.findByCif("2222"));

		manufacturerService.findAllByAddressCity("city M1").forEach(System.out::println);
	}

}
