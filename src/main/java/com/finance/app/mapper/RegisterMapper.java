package com.finance.app.mapper;

import com.finance.app.dto.RegisterDto;
import com.finance.app.models.Register;

public class RegisterMapper {
    public static RegisterDto mapToRegisterDto(Register register) {
        RegisterDto registerDto = RegisterDto.builder()
                .id(register.getId())
                .username(register.getUsername())
                .email(register.getEmail())
                .password(register.getPassword())
                .createdOn(register.getCreatedOn())
                .updatedOn(register.getUpdatedOn())
                .build();
        return registerDto;
    }

}
