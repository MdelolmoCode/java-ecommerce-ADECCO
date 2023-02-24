package com.example.services;

import com.example.entities.Manufacturer;
import com.example.exception.EntityDeleteException;
import com.example.exception.EntitySavingException;
import com.example.repositories.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerRepository manufacturerRepo;

    @Override
    public List<Manufacturer> findAll() {
        log.info("findAll");
        return manufacturerRepo.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return manufacturerRepo.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByCif(String cif) {
        log.info("findByCif {}", cif);
        if (!StringUtils.hasLength(cif))
            return Optional.empty();
        return manufacturerRepo.findByCif(cif);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        log.info("findById {}", name);
        if (!StringUtils.hasLength(name))
            return Optional.empty();
        return manufacturerRepo.findByName(name);
    }

    @Override
    public List<Manufacturer> findAllByAddressCity(String city) {
        log.info("findAllByAddressCity {}", city);
        if (!StringUtils.hasLength(city))
            return new ArrayList<>();
        return manufacturerRepo.findAllByAddressCity(city);
    }

    @Override
    public Optional<Manufacturer> findByPhoneNumber(String phoneNumber) {
        log.info("findById {}", phoneNumber);
        if (!StringUtils.hasLength(phoneNumber))
            return Optional.empty();
        return manufacturerRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        log.info("save {}", manufacturer);
        if(manufacturer == null)
            throw new IllegalArgumentException("Manufacturer nulo.");

        if(manufacturer.getId() != null)
            return update(manufacturer);

        try {
            return manufacturerRepo.save(manufacturer);
        } catch (Exception e) {
            log.error("Error al guardar manufacturer.", e);
        }

        throw new EntitySavingException("Error al guardar manufacturer.");
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        log.info("update {}", manufacturer);
        if(manufacturer == null)
            throw new IllegalArgumentException("Manufacturer nulo.");

        if(manufacturer.getId() == null)
            throw new IllegalArgumentException("Manufacturer con ID nulo.");

        Optional<Manufacturer> manufacturerOpt = manufacturerRepo.findById(manufacturer.getId());
        if (manufacturerOpt.isEmpty())
            throw new EntityNotFoundException("No existe un manufacturer con ese ID.");

        Manufacturer manufacturerFromDB = manufacturerOpt.get();
        manufacturerFromDB.setCif(manufacturer.getCif());
        manufacturerFromDB.setName(manufacturer.getName());
        manufacturerFromDB.setAddress(manufacturer.getAddress());
        manufacturerFromDB.setPhoneNumber(manufacturer.getPhoneNumber());

        try {
            return manufacturerRepo.save(manufacturerFromDB);
        } catch (Exception e) {
            log.error("Error al guardar manufacturer.", e);
        }

        throw new EntitySavingException("Error al guardar manufacturer.");
    }

    @Override
    public void deleteById(Long id) {
        log.info("deleteById {}", id);
        try {
            manufacturerRepo.deleteById(id);
            return;
        } catch (Exception e) {
            log.error("Error al borrar manufacturer.", e);
        }

        throw new EntityDeleteException("Error al borrar manufacturer.");
    }
}
