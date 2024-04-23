package com.finance.app.controller;

import com.finance.app.dto.LoginRequest;
import com.finance.app.models.Register;
import com.finance.app.security.JwtTokenProvider;
import com.finance.app.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginServiceImpl loginServiceImpl;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginController(LoginServiceImpl loginServiceImpl, JwtTokenProvider jwtTokenProvider) {
        this.loginServiceImpl = loginServiceImpl;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Optional<Register> loginResult = loginServiceImpl.login(username, password);

        if (loginResult.isPresent()) {
            Register user = loginResult.get();
            String userId = String.valueOf(user.getId());
            String token = jwtTokenProvider.generateToken(userId, username);

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}