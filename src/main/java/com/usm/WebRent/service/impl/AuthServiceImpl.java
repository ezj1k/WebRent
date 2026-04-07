package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Users;
import com.usm.WebRent.entity.enums.UserStatus;
import com.usm.WebRent.service.AuthService;
import com.usm.WebRent.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersService usersService;

    @Override
    public Users login(String email, String password) {
        Users user = usersService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public void register(Users user) {
        user.setStatus(UserStatus.CUSTOMER);
        user.setCreatedAt(java.time.LocalDateTime.now());
        usersService.save(user);
    }
}