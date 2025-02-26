package com.portalst.cadastroempresa.controllers;

import com.portalst.cadastroempresa.dto.LoginRequest;
import com.portalst.cadastroempresa.dto.AuthResponse; 
import com.portalst.cadastroempresa.models.Users;
import com.portalst.cadastroempresa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            return ResponseEntity.ok(new AuthResponse(token)); // Retorna o token no formato JSON
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
