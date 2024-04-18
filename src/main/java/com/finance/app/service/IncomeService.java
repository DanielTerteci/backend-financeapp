package com.finance.app.service;

import com.finance.app.dto.IncomeDto;
import com.finance.app.models.Expense;
import com.finance.app.models.Income;

import java.util.List;

public interface IncomeService {
    List<IncomeDto> findAllIncomes();

//    Income saveIncome(Income income);

    Income getIncomeById(int incomeId);

    IncomeDto saveIncomeWithCategory(int categoryId, IncomeDto incomeDto);
}
