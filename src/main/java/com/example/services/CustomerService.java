package com.example.services;

import com.example.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    // CRUD

    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Optional <Customer> findBySurname(String surname);
    Customer findByEmail(String email);

    public Customer save(Customer customer);
    public void update(Customer customer);
    public void deleteById(Long id);





}
