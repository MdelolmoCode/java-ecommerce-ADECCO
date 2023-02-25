package com.example.services;

import com.example.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService
{
    //=====================  CRUD  ============================

    List<Address> findAll();

    boolean existsById(Long id);

    Optional<Address> findById(Long id);

    Address save(Address address);

    Address update(Address address);

    void deleteById(Long id);

    //===============================  FILTERS  =========================================

    List<Address> findByCityAndState(String city, String state);
    List<Address> findByCountry(String country);
    List<Address> findByZipcode(String zipcode);



}
