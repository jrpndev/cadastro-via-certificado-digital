package com.portalst.cadastroempresa.controllers;

import com.portalst.cadastroempresa.models.Users;
import com.portalst.cadastroempresa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }
}
