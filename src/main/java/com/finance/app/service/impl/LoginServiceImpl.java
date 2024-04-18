package com.finance.app.service.impl;

import com.finance.app.models.Register;
import com.finance.app.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl {
    private RegisterRepository registerRepository;

    @Autowired
    public LoginServiceImpl(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Optional<Register> login(String username, String password) {
        return registerRepository.findByUsernameAndPassword(username, password);
    }
}
