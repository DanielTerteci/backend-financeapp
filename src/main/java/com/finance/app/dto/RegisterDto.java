package com.finance.app.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterDto {
    private int id;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "You must introduce your e-mail address !")
    private String email;
    @NotEmpty(message = "Password field should not be empty")
    private String password;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
