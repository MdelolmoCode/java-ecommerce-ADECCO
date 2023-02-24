package com.example.services;

import com.example.entities.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> findByCif(String cif);
    Optional<Manufacturer> findByName(String name);
    List<Manufacturer> findAllByAddressCity(String city);
    Optional<Manufacturer> findByPhoneNumber(String phoneNumber);

    Manufacturer save(Manufacturer manufacturer);
    Manufacturer update(Manufacturer manufacturer);
    void deleteById(Long id);

}
