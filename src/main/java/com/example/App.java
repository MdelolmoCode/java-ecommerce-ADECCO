package com.example;

import com.example.entities.Address;
import com.example.entities.Category;
import com.example.entities.Manufacturer;
import com.example.entities.Order;
import com.example.entities.Product;
import com.example.repositories.AddressRepository;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import com.example.services.ProductService;
import com.example.services.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class App {

	static ApplicationContext context;
	public static void main(String[] args) {
		// Inicializa spring
		context = SpringApplication.run(App.class, args);

		// AddressService addressService = context.getBean(AddressService.class);
		// CategoryService categoryService = context.getBean(CategoryService.class);
		// ManufacturerService manufacturerService = context.getBean(ManufacturerService.class);
		ProductService productService = context.getBean(ProductService.class);

		createDemoData();

		System.out.println("------============ AQUI EMPIEZA ============-------------");
		for(Product product : productService.findAllByPriceBetween(18.00,88.0))
			System.out.println(product);


	}

	private static void createDemoData() {
/*

		// categories
		CategoryRepository categoryRepo = context.getBean(CategoryRepository.class);
		Category category1 = new Category("category1",false);
		Category category2 = new Category("category2",false);
		Category category3 = new Category("category3",false);
		Category category4 = new Category("category4",true);
		// categoryRepo.saveAll(category1,category2,category3,category4);
		categoryRepo.save(category1);
		categoryRepo.save(category2);
		categoryRepo.save(category3);
		categoryRepo.save(category4);

		*/
/*List<Category> categories1 = new ArrayList<>();
		categories1.add(category1);
		categories1.add(category2);

		List<Category> categories2 = new ArrayList<>();
		categories2.add(category1);
		categories2.add(category3);

		List<Category> categories3 = new ArrayList<>();
		categories3.add(category3);
		categories3.add(category4);*//*



		// address
		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		Address address1 = new Address(1L, "street1","name1","city1","state1","country1","zipcode1");
		Address address2 = new Address(1L, "street2","name2","city2","state2","country1","zipcode2");
		// addressRepo.saveAll(address1, address2);
		addressRepo.save(address1);
		addressRepo.save(address2);

		// manufacturer
		ManufacturerRepository manufacturerRepo = context.getBean(ManufacturerRepository.class);
		Manufacturer manufacturer1 = new Manufacturer(null,"cif1","name1",address1,"1234phone");
		Manufacturer manufacturer2 = new Manufacturer(null,"cif2","name2",address2,"5678phone");
		// manufacturerRepo.saveAll(manufacturer1, manufacturer2);
		manufacturerRepo.save(manufacturer1);
		manufacturerRepo.save(manufacturer2);

*/

		// product
		// productos sin ID, Category ni Manufacturer
		ProductRepository productRepo = context.getBean(ProductRepository.class);
		Product product1 = new Product("pincel", "desc pincel", 10.99, 10L,true);
		/*product1.getCategories().add(category1);
		product1.getCategories().add(category2);*/
		Product product2 = new Product("botella", "desc botella", 20.99, 20L,true);
		/*product2.getCategories().add(category1);
		product2.getCategories().add(category3);*/
		Product product3 = new Product("pegamento", "desc pegamento", 30.99, 30L,true);
		/*product3.getCategories().add(category2);
		product3.getCategories().add(category4);*/
		Product product4 = new Product("lámpara", "desc lámpara", 40.99, 40L,true);
		Product product5 = new Product("monedero de cuero", "desc monedero de cuero", 50.99, 50L,true);
		Product product6 = new Product("jaula de pájaro", "desc jaula de pájaro", 60.99, 60L,true);
		Product product7 = new Product("hilo negro", "desc hilo negro", 70.99, 70L,false);
		Product product8 = new Product("set cubertería", "desc set cubertería", 80.99, 0L,false);
		Product product9 = new Product("ordenador", "desc ordenador", 90.99, 90L,true);
		Product product10 = new Product("pistola", "desc pistola", 100.99, 100L,true);


		productRepo.saveAll(List.of(product1,product2,product3,product4,product5,
				product6,product7,product8, product9, product10));



		// testOrder(context);
	}

	private static void testOrder(ApplicationContext context) {
		Address address1 = new Address(null, "street1","name1","city1","state1","country1","zipcode1");
		Address address2 = new Address(null, "street2","name2","city2","state2","country1","zipcode2");
		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		addressRepo.saveAll(List.of(address1, address2));

		OrderService orderService = context.getBean(OrderService.class);
		Order order1 = new Order(null, 1000L, address1);
		Order order2 = new Order(null, 2000L, address2);
		Order order3 = new Order(null, 3000L, address2);
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
	}

}
