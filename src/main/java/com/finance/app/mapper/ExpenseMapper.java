package com.finance.app.mapper;

import com.finance.app.dto.CategoryDto;
import com.finance.app.dto.ExpenseDto;
import com.finance.app.models.Category;
import com.finance.app.models.Expense;
import com.finance.app.models.Register;

import static com.finance.app.mapper.CategoryMapper.mapToCategoryDto;

public class ExpenseMapper {
    public static ExpenseDto mapToExpenseDto(Expense expense) {
        ExpenseDto expenseDto = ExpenseDto.builder()
                .id(expense.getId())
                .currency(expense.getCurrency())
                .isRecurrent(expense.getIsRecurrent())
                .price(expense.getPrice())
                .expenseDate(expense.getExpenseDate())
                .category(mapToCategoryDto(expense.getCategory()))
                .userId(expense.getUserId() != null ? expense.getUserId().getId() : null)
                .createdOn(expense.getCreatedOn())
                .updatedOn(expense.getUpdatedOn())
                .build();
        return expenseDto;
    }

    public static Expense mapToExpense(ExpenseDto expenseDto) {
        Expense expense = Expense.builder()
                .id(expenseDto.getId())
                .currency(expenseDto.getCurrency())
                .isRecurrent(expenseDto.getIsRecurrent())
                .price(expenseDto.getPrice())
                .expenseDate(expenseDto.getExpenseDate())
                .userId(Register.builder().id(expenseDto.getUserId()).build())
                .createdOn(expenseDto.getCreatedOn())
                .updatedOn(expenseDto.getUpdatedOn())
                .build();
        return expense;
    }
}
