package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Users;
import com.usm.WebRent.entity.enums.UserStatus;
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
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id).orElseThrow(()-> new RuntimeException("User with id:" + id + "doesn't exist"));
    }

    @Override
    public Users update(Long id, Users usersDetails) {
        Users users = findById(id);

        users.setCreatedAt(usersDetails.getCreatedAt());
        users.setEmail(usersDetails.getEmail());
        users.setPhone(usersDetails.getPhone());
        users.setPassword(usersDetails.getPassword());
        users.setStatus(usersDetails.getStatus());
        users.setDriverLicenseNumber(usersDetails.getDriverLicenseNumber());
        users.setFirstName(usersDetails.getFirstName());
        users.setLastName(usersDetails.getLastName());

        return usersRepository.save(users);
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
