package com.example;



import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;
import com.example.services.CustomerService;
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

		Customer customer1 = new Customer(null, "Alex", "Bustamante", "albusor@gmail.com", null, "665167676");
		Customer customer2 = new Customer(null, "Zaira", "Gamez", "zairagmez@gmail.com", null, "665453323");
		Customer customer3 = new Customer(null, "Zaira", "Gamez", "zairagmez@hotmail.com", null, "44333212342");

		customerRepository.saveAll(List.of(customer1,customer2));

		//List<Customer> aux = customerService.findAll();
		//Optional <Customer> aux = customerService.findBySurname("Bustaman");
		//Optional<Customer> aux = customerService.findById(1L);
		//Optional<Customer> aux = customerService.findByEmail("albusor@gail.com");
		//Customer aux = customerService.save(customer3);
		//Optional <Customer> saved = customerService.findBySurname("Gamez");
		System.out.println(customerService.findAll());
		customerService.deleteById(1L);



		System.out.println(customerService.findAll());




	}
}
