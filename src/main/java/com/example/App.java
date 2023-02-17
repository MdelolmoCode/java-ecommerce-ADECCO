package com.example;

import com.example.entities.*;
import com.example.repositories.*;
import com.example.services.CategoryService;
import com.example.services.ProductService;
import com.example.services.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {


	public static void main(String[] args) {


		ApplicationContext context = SpringApplication.run(App.class, args);

		CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

		Customer customer1 = new Customer(null, "Alex", "Bustamante", "alex@gmail.com", null, "667672627");

		customerRepository.save(customer1);


	}
}
