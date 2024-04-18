package com.finance.app.service.impl;

import com.finance.app.dto.RegisterDto;
import com.finance.app.exception.DuplicateEmailException;
import com.finance.app.exception.DuplicateUsernameException;
import com.finance.app.exception.InvalidPasswordFormatException;
import com.finance.app.exception.InvalidUsernameFormatException;
import com.finance.app.models.Register;
import com.finance.app.repository.RegisterRepository;
import com.finance.app.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.finance.app.mapper.RegisterMapper.mapToRegisterDto;

@Service
public class RegisterServiceImpl implements RegisterService {
    private RegisterRepository registerRepository;
    @Autowired

    public RegisterServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    @Override
    public List<RegisterDto> findAllUsers() {
        List<Register> login = registerRepository.findAll();
        return login.stream().map((login1 -> mapToRegisterDto(login1))).collect(Collectors.toList());
    }

    public void saveUser(RegisterDto registerDto) {
        if (registerRepository.existsByEmail(registerDto.getEmail())) {
            throw new DuplicateEmailException("Email address is already in use");
        }

        if (registerRepository.existsByUsername(registerDto.getUsername())) {
            throw new DuplicateUsernameException("Username is already in use");
        }

        if(registerDto.getUsername() == null || registerDto.getUsername().length()<5){
            throw new InvalidUsernameFormatException("Username must be at least 5 characters long");
        }

        if (registerDto.getPassword() == null || registerDto.getPassword().length() < 8
                || !registerDto.getPassword().chars().anyMatch(Character::isUpperCase)
                || !registerDto.getPassword().chars().anyMatch(ch -> "!@#$%^&*()-_=+{};:',<.>/?".indexOf(ch) >= 0)) {
            throw new InvalidPasswordFormatException("Password must contain at least 8 characters, 1 capital letter, 1 symbol");
        }
        try {
            Register register = Register.builder()
                    .username(registerDto.getUsername())
                    .email(registerDto.getEmail())
                    .password(registerDto.getPassword())
                    .build();
            registerRepository.save(register);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Email address is already in use");
        }
    }

    @Override
    public Optional<Register> findByEmail(String email) {
        return registerRepository.findByEmail(email);
    }

    @Override
    public Optional<Register> findByUsername(String username) {
        return registerRepository.findByUsername(username);
    }

}
