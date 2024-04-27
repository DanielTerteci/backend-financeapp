package com.finance.app.mapper;

import com.finance.app.dto.IncomeDto;
import com.finance.app.models.Income;
import com.finance.app.models.Register;
import org.springframework.security.core.userdetails.User;

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
//                .username(income.getUserId().getUsername())
//                .email(income.getUserId().getEmail())
                .createdOn(income.getCreatedOn())
                .updatedOn(income.getUpdatedOn())
                .build();
        return incomeDto;
    }

    public static Income mapToIncome(IncomeDto incomeDto) {
        return Income.builder()
                .id(incomeDto.getId())
                .currency(incomeDto.getCurrency())
                .isRecurrent(incomeDto.getIsRecurrent())
                .price(incomeDto.getPrice())
                .incomeDate(incomeDto.getIncomeDate())
                .userId(incomeDto.getUserId())
                .createdOn(incomeDto.getCreatedOn())
                .updatedOn(incomeDto.getUpdatedOn())
                .build();
    }
}
