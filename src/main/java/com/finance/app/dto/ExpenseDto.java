package com.finance.app.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExpenseDto {
    private int id;
    private Boolean isRecurrent;
    private String currency;
    @NotEmpty(message = "Price should not be empty")
    private Float price;
    private CategoryDto category;
    private LocalDate expenseDate;
    private Integer userId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
