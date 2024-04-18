package com.finance.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finance.app.enums.CategoryType;
import com.finance.app.models.Expense;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private int id;
    private CategoryType type;
    private String name;

}
