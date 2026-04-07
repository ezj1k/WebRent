package com.usm.WebRent.service;

import com.usm.WebRent.entity.Users;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    Users login(String email, String password);
    void register(Users user);
}