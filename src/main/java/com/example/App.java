package com.example;

import com.example.entities.Address;
import com.example.entities.Category;
import com.example.entities.Manufacturer;
import com.example.entities.Product;
import com.example.repositories.AddressRepository;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import com.example.services.ProductService;
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
		// CategoryService categoryService = context.getBean(CategoryService.class);
		// ManufacturerService manufacturerService = context.getBean(ManufacturerService.class);
		ProductService productService = context.getBean(ProductService.class);

		createDemoData();

		for(Product product : productService.findByPriceBetween(8.00,25.0))
			System.out.println(product);

		// productService.findByPriceBetween(8.00,25.0);

	}

	private static void createDemoData() {

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

		/*List<Category> categories1 = new ArrayList<>();
		categories1.add(category1);
		categories1.add(category2);

		List<Category> categories2 = new ArrayList<>();
		categories2.add(category1);
		categories2.add(category3);

		List<Category> categories3 = new ArrayList<>();
		categories3.add(category3);
		categories3.add(category4);*/


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


		// product
		ProductRepository productRepo = context.getBean(ProductRepository.class);
		Product product1 = new Product("name1","description1",10.99,10L,true,manufacturer1);
		product1.getCategories().add(category1);
		product1.getCategories().add(category2);
		Product product2 = new Product("name2","description2",20.99,20L,true,manufacturer1);
		product2.getCategories().add(category1);
		product2.getCategories().add(category3);
		Product product3 = new Product("name3","description3",30.99,30L,true,manufacturer2);
		product3.getCategories().add(category2);
		product3.getCategories().add(category4);


		// productRepo.saveAll(product1,product2,product3);
		productRepo.save(product1);
		productRepo.save(product2);
		productRepo.save(product3);

	}

}
