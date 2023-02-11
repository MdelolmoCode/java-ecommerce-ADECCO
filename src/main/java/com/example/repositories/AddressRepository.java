package com.example.repositories;

import com.example.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCityAndState(String city, String state);
    List<Address> findByCountry(String country);
    List<Address> findByZipcode(String zipcode);






}