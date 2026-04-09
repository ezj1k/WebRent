package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Review;
import com.usm.WebRent.entity.Users;
import com.usm.WebRent.entity.enums.UserStatus;
import com.usm.WebRent.exception.EmailAlreadyExistsException;
import com.usm.WebRent.exception.EmptyListException;
import com.usm.WebRent.exception.UserNotFoundException;
import com.usm.WebRent.exception.UserUpdateException;
import com.usm.WebRent.repository.UsersRepository;
import com.usm.WebRent.service.UsersService;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Users save(Users users) {
        // Verificăm dacă email-ul există deja înainte de a salva
        if (usersRepository.findByEmail(users.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(users.getEmail());
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        List<Users> users = usersRepository.findAll();
        if (users.isEmpty()) {
            throw new EmptyListException("Users");
        }
        return users;
    }

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Users update(Long id, Users usersDetails) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        try {
            user.setEmail(usersDetails.getEmail());
            user.setCreatedAt(usersDetails.getCreatedAt());
            user.setPhone(usersDetails.getPhone());
            user.setPassword(usersDetails.getPassword());
            user.setStatus(usersDetails.getStatus());
            user.setDriverLicenseNumber(usersDetails.getDriverLicenseNumber());
            user.setFirstName(usersDetails.getFirstName());
            user.setLastName(usersDetails.getLastName());

            return usersRepository.save(user);
        } catch (Exception e) {
            throw new UserUpdateException("Datele furnizate pentru actualizare sunt invalide.");
        }
    }

    @Override
    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }



    @Override
    public Users findByEmail(String email){
        return usersRepository.findByEmail(email).orElse(null);
    }

}
