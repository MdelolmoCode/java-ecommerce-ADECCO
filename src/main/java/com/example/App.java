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

		Customer customer1 = new Customer(null, "Pedro", "Rodriguez", "pedrorod@gmail.com", null, "665167676");
		Customer customer2 = new Customer(null, "Angela", "Diaz", "angeladiaz@gmail.com", null, "665453323");

		customerRepository.saveAll(List.of(customer1,customer2));






	}
}
