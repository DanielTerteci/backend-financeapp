package com.finance.app.service;

import com.finance.app.dto.RegisterDto;
import com.finance.app.models.Register;

import java.util.List;
import java.util.Optional;

public interface RegisterService {
    List<RegisterDto> findAllUsers();

    void saveUser(RegisterDto registerDto);
    Optional<Register> findByUsername(String userName);
    Optional<Register> findByEmail(String email);
}
