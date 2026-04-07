package com.usm.WebRent.controller;

import com.usm.WebRent.entity.Users;
import com.usm.WebRent.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users loginRequest) {
        Users user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(401).body("Email sau parolă incorectă!");
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        authService.register(user);
        return ResponseEntity.status(201).body("Utilizator înregistrat cu succes!");
    }
}