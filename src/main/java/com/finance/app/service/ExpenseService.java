package com.finance.app.service;

import com.finance.app.dto.ExpenseDto;
import com.finance.app.models.Expense;

import java.util.List;

public interface ExpenseService {
    ExpenseDto saveExpenseWithCategory(int categoryId,ExpenseDto expenseDto);

    Expense getExpenseById(int expenseId);

    List<ExpenseDto> findAllExpenses();
}
