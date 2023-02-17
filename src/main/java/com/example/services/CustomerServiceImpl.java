package com.example.services;

import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    //métodos

    @Override
    public List<Customer> findAll() { //tested
        log.info("Ejecutando método findAll() from CustomerService");
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) { //tested
        log.info("Ejecutando método findById() from CustomerService {}", id);
        return customerRepository.findById(id);
    }


    @Override
    public Optional <Customer> findBySurname(String surname) { //tested
        log.info("Ejecutando método findBySurname() from CustomerService {}", surname);
        return customerRepository.findBySurname(surname);
    }

    @Override
    public Optional <Customer> findByEmail(String email) { // tested
        log.info("Ejecutando método findByEmail() from CustomerService {}", email);
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        log.info("Ejecutando método findByPhone() from CustomerService {}", phone);
        return customerRepository.findByPhone(phone);
    }


    @Override
    public Customer save(Customer customer) { // tested

        if(customer == null){
            throw new IllegalArgumentException("Customer no puede ser null");
        }
        if(customer != null)
            update(customer);
         return customerRepository.save(customer);

    }
    @Override
    public void update(Customer customer) { //tested

        Customer customerFromDB = customerRepository.findById(customer.getId()).get();
        customerFromDB.setName(customer.getName());
        customerFromDB.setSurname(customer.getSurname());
        customerFromDB.setEmail(customer.getEmail());
        customerFromDB.setAddresses(customer.getAddresses());
        customerFromDB.setPhone(customer.getPhone());

        customerRepository.save(customerFromDB);
    }

    @Override
    public void deleteById(Long id) { // tested

        customerRepository.deleteById(id);

    }
}
