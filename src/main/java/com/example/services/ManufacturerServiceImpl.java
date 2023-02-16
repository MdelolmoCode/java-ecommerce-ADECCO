package com.example.services;

import com.example.entities.Address;
import com.example.entities.Manufacturer;
import com.example.repositories.ManufacturerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private ManufacturerRepository ManufacturerRepo;

    @Override
    public List<Manufacturer> findAll() {
        log.info("findAll");
        return ManufacturerRepo.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return ManufacturerRepo.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByCif(String cif) {
        log.info("findByCif {}", cif);
        if (cif == null)
            return Optional.empty();
        return ManufacturerRepo.findByCif(cif);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        log.info("findById {}", name);
        if (name == null)
            return Optional.empty();
        return ManufacturerRepo.findByName(name);
    }

    @Override
    public Optional<Manufacturer> findByPhoneNumber(String phoneNumber) {
        log.info("findById {}", phoneNumber);
        if (phoneNumber == null)
            return Optional.empty();
        return ManufacturerRepo.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        log.info("save {}", manufacturer);
        if(manufacturer == null) {
            throw new IllegalArgumentException("Manufacturer can't be null");
        }
        if(manufacturer != null)
            update(manufacturer);
        return ManufacturerRepo.save(manufacturer);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        if(manufacturer == null)
            throw new IllegalArgumentException("Manufacturer can't be null");

        if(manufacturer.getId() == null)
            throw new IllegalArgumentException("Manufacturer ID can't null");

        if(!ManufacturerRepo.existsById(manufacturer.getId()))
            throw new EntityNotFoundException("Category does not exist");

        Manufacturer manufacturerFromDB = ManufacturerRepo.findById(manufacturer.getId()).get();
        manufacturerFromDB.setCif(manufacturer.getCif());
        manufacturerFromDB.setName(manufacturer.getName());
        manufacturerFromDB.setPhoneNumber(manufacturer.getPhoneNumber());

        return ManufacturerRepo.save(manufacturerFromDB);
    }

    @Override
    public void deleteById(Long id) {
        log.info("DeleteById {}", id);
        ManufacturerRepo.deleteById(id);
    }
}
