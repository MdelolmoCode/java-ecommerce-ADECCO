package com.example.services;

import com.example.entities.UserEntity;
import com.example.exception.EntitySavingException;
import com.example.repositories.CustomerRepository;
import com.example.repositories.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserEntityRepository userRepo;
    private final CustomerRepository customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername {}", username);
        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
        if(userOpt.isPresent())
            return userOpt.get();
        else
            throw new UsernameNotFoundException("Usuario " + username + "no encontrado");
    }

    @Override
    public boolean existsByUsername(String username) {
        log.info("existsByUsername {}", username);
        return userRepo.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("existsByEmail {}", email);
        return customerRepo.existsByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity user) {
        log.info("save {}", user);

        try {
            customerRepo.save(user.getCustomer());
            return userRepo.save(user);
        } catch (Exception e) {
            log.error("Error al guardar user.", e);
        }

        throw new EntitySavingException("Error al guardar user.");
    }
}
