package com.example.services;

import com.example.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findAll();
    Optional<Address> findById(Long id);
    List<Address> findByCityAndState(String city, String state);
    List<Address> findByCountry(String country);
    List<Address> findByZipcode(String zipcode);

    Address save(Address address);
    Address update(Address address);
    void deleteById(Long id);

}
