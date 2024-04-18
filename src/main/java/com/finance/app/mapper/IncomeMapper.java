package com.finance.app.mapper;

import com.finance.app.dto.IncomeDto;
import com.finance.app.models.Income;
import com.finance.app.models.Register;

import static com.finance.app.mapper.CategoryMapper.mapToCategoryDto;

public class IncomeMapper {
    public static IncomeDto mapToIncomeDto(Income income) {
        IncomeDto incomeDto = IncomeDto.builder()
                .id(income.getId())
                .currency(income.getCurrency())
                .isRecurrent(income.getIsRecurrent())
                .price(income.getPrice())
                .category(mapToCategoryDto(income.getCategory()))
                .incomeDate(income.getIncomeDate())
                .userId(income.getUserId() != null ? income.getUserId().getId() : null)
                .createdOn(income.getCreatedOn())
                .updatedOn(income.getUpdatedOn())
                .build();
        return incomeDto;
    }

    public static Income mapToIncome(IncomeDto incomeDto) {
        Income income = Income.builder()
                .id(incomeDto.getId())
                .currency(incomeDto.getCurrency())
                .isRecurrent(incomeDto.getIsRecurrent())
                .price(incomeDto.getPrice())
                .incomeDate(incomeDto.getIncomeDate())
                .userId(Register.builder().id(incomeDto.getUserId()).build())
                .createdOn(incomeDto.getCreatedOn())
                .updatedOn(incomeDto.getUpdatedOn())
                .build();
        return income;
    }
}
