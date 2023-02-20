package com.example.services;

import com.example.entities.Address;
import com.example.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepo;
    @Override
    public List<Address> findAll() {
        log.info("findAll");
        return addressRepo.findAll();
    }

    public boolean existsById(Long id) throws IllegalArgumentException
    {
        return addressRepo.existsById(id);
    }

    @Override
    public Optional<Address> findById(Long id) {
        log.info("findById {}", id);
        if (id == null || id <= 0)
            return Optional.empty();
        return addressRepo.findById(id);
    }

    @Override
    public List<Address> findByCityAndState(String city, String state) {
        log.info("findByCityAndState city {} state {}", city, state);
        return addressRepo.findByCityAndState(city, state);
    }

    @Override
    public List<Address> findByCountry(String country) {
        log.info("findByCountry {}", country);
        return addressRepo.findByCountry(country);
    }

    @Override
    public List<Address> findByZipcode(String zipcode) {
        log.info("findByZipcode {}", zipcode);
        return addressRepo.findByZipcode(zipcode);
    }

    @Override
    public Address save(Address address) {
        log.info("save {}", address);
        if(address == null) {
            throw new IllegalArgumentException("Address can't be null");
        }
        if(address.getId() != null)
            update(address);
        return addressRepo.save(address);
    }

    @Override
    public Address update(Address address) {
        if(address == null)
            throw new IllegalArgumentException("Address can't be null");

        if(address.getId() == null)
            throw new IllegalArgumentException("Address ID can't null");

        if(!addressRepo.existsById(address.getId()))
            throw new EntityNotFoundException("Address does not exist");

        Address addressFromDB = addressRepo.findById(address.getId()).get();
        addressFromDB.setName(address.getName());
        addressFromDB.setCity(address.getCity());
        addressFromDB.setState(address.getState());

        return addressRepo.save(addressFromDB);
    }

    @Override
    public void deleteById(Long id) {
        log.info("DeleteById {}", id);
        addressRepo.deleteById(id);
    }
}
