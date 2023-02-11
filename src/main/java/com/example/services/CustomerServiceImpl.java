package com.example.services;

import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;


public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    //Constructor

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //métodos

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }


    @Override
    public Optional <Customer> findBySurname(String surname) {
        return this.customerRepository.findBySurname(surname);
    }

    @Override
    public Customer findByEmail(String email) {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void save() {

    }

    @Override
    public void update() {

    }

    @Override
    public void deleteCustomer() {

    }
}
