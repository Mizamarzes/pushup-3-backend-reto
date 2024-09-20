package com.mizamarzes.pushup_3_backend.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizamarzes.pushup_3_backend.infrastructure.adpters.AuthAdapter;
import com.mizamarzes.pushup_3_backend.infrastructure.entities.AuthResponse;
import com.mizamarzes.pushup_3_backend.infrastructure.entities.LoginRequest;
import com.mizamarzes.pushup_3_backend.infrastructure.entities.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthAdapter authAdapter;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authAdapter.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authAdapter.register(request));
    }
}
