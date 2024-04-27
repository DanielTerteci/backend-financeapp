package com.finance.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finance.app.models.Register;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class IncomeDto {
    private int id;
    private Boolean isRecurrent;
    private String currency;
    @NotEmpty(message = "Price should not be empty")
    private Float price;
    private CategoryDto category;
    private LocalDate incomeDate;
    private Integer userId;
//    private String username;
//    private String email;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
