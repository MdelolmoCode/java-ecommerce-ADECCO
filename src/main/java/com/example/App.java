package com.example;

import com.example.entities.Address;
import com.example.entities.Manufacturer;
import com.example.entities.Product;
import com.example.repositories.AddressRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		// Inicializa spring
		ApplicationContext context = SpringApplication.run(App.class, args);

		// manufacturer and address
		AddressRepository addressRepo = context.getBean(AddressRepository.class);
		Address address1 = new Address(null, "street1","name1","city1","state1","country1","zipcode1");
		Address address2 = new Address(null, "street2","name2","city2","state2","country1","zipcode2");
		addressRepo.save(address1);
		addressRepo.save(address2);

		ManufacturerRepository manufacturerRepo = context.getBean(ManufacturerRepository.class);
		Manufacturer manufacturer1 = new Manufacturer(null,"cif1","name1",address1,"1234phone");
		Manufacturer manufacturer2 = new Manufacturer(null,"cif2","name2",address2,"5678phone");
		manufacturerRepo.save(manufacturer1);
		manufacturerRepo.save(manufacturer2);

		// product
		ProductRepository productRepo = context.getBean(ProductRepository.class);
		Product product1 = new Product(null,"name1","description1",10.99D,"category1",
				manufacturer1,10L);
		Product product2 = new Product(null,"name2","description2",20.99D,"category2",
				manufacturer1,20L);
		Product product3 = new Product(null,"name3","description3",30.99D,"category2",
				manufacturer2,30L);
		productRepo.save(product1);
		productRepo.save(product2);
		productRepo.save(product3);



	}

}
