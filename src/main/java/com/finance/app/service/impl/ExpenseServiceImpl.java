package com.finance.app.service.impl;

import com.finance.app.dto.CategoryDto;
import com.finance.app.dto.ExpenseDto;
import com.finance.app.mapper.CategoryMapper;
import com.finance.app.mapper.ExpenseMapper;
import com.finance.app.models.Category;
import com.finance.app.models.Expense;
import com.finance.app.models.Register;
import com.finance.app.repository.CategoryRepository;
import com.finance.app.repository.ExpenseRepository;
import com.finance.app.repository.RegisterRepository;
import com.finance.app.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.finance.app.enums.CategoryType.expense;
import static com.finance.app.mapper.CategoryMapper.mapToCategoryDto;
import static com.finance.app.mapper.ExpenseMapper.mapToExpenseDto;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseDto saveExpenseWithCategory(int categoryId, ExpenseDto expenseDto) {
        Integer userId = expenseDto.getUserId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryDto categoryDto = CategoryMapper.mapToCategoryDto(category);
        expenseDto.setCategory(categoryDto);

        Register user = userId != null ? registerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")) : null;

        Expense newExpense = ExpenseMapper.mapToExpense(expenseDto);
        newExpense.setCategory(category);

        try {
            Expense savedExpense = expenseRepository.save(newExpense);
            return ExpenseMapper.mapToExpenseDto(savedExpense);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save the expense", e);
        }
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        Optional<Expense> optionalExpense = expenseRepository.findById(expenseId);
        return optionalExpense.orElse(null);
    }

    @Override
    public List<ExpenseDto> findAllExpenses() {
        List<Expense> expense = expenseRepository.findAll();
        return expense.stream().map((expense1 -> mapToExpenseDto(expense1))).collect(Collectors.toList());
    }

}
