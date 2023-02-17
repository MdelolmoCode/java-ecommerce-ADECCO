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
    public List<Customer> findAll() {
        log.info("Ejecutandoo FindAll()");
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        log.info("findById {}", id);
        return customerRepository.findById(id);
    }


    @Override
    public Optional <Customer> findBySurname(String surname) {
        log.info("findBySurname {}", surname);
        return customerRepository.findBySurname(surname);
    }

    @Override
    public Customer findByEmail(String email) {
        log.info("findByEmail {}", email);
        return customerRepository.findByEmail(email);
    }


    @Override
    public Customer save(Customer customer) {

        if(customer == null){
            throw new IllegalArgumentException("Customer no puede ser null");
        }
        if(customer != null)
            update(customer);
         return customerRepository.save(customer);

    }
    @Override
    public void update(Customer customer) {

        Customer customerFromDB = customerRepository.findById(customer.getId()).get();
        customerFromDB.setName(customer.getName());
        customerFromDB.setSurname(customer.getSurname());
        customerFromDB.setEmail(customer.getEmail());
        customerFromDB.setAddresses(customer.getAddresses());
        customerFromDB.setPhone(customer.getPhone());

        customerRepository.save(customerFromDB);
    }

    @Override
    public void deleteById(Long id) {

        customerRepository.deleteById(id);

    }
}
