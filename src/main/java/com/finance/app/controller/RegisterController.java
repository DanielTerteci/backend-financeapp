package com.finance.app.controller;

import com.finance.app.dto.RegisterDto;
import com.finance.app.exception.DuplicateEmailException;
import com.finance.app.exception.DuplicateUsernameException;
import com.finance.app.exception.InvalidPasswordFormatException;
import com.finance.app.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/register")
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public ResponseEntity<List<RegisterDto>> listRegister() {
        List<RegisterDto> register = registerService.findAllUsers();
        return ResponseEntity.ok(register);
    }

    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            StringBuilder errorMessage = new StringBuilder("Validation error(s): ");
            for (FieldError fieldError : fieldErrors) {
                errorMessage.append(fieldError.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }

        try {
            registerService.saveUser(registerDto);
            return ResponseEntity.ok("Registration successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

