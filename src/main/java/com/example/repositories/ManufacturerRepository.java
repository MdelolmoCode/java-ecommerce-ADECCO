package com.example.repositories;

import com.example.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByCif(String cif);
    Optional<Manufacturer> findByName(String name);
    Optional<Manufacturer> findByPhoneNumber(String phoneNumber);







}