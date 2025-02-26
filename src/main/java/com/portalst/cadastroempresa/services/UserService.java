package com.portalst.cadastroempresa.services;
import com.portalst.cadastroempresa.models.Users;
import com.portalst.cadastroempresa.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Value("${jwt.secret}")
    private String secret;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Users registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {
        Users user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateToken(user);
        }
        return null;
    }

    private String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userType", user.getUserType().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
