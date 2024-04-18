package com.finance.app.controller;

import com.finance.app.dto.CategoryDto;
import com.finance.app.dto.ExpenseDto;
import com.finance.app.mapper.ExpenseMapper;
import com.finance.app.models.Expense;
import com.finance.app.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.finance.app.mapper.ExpenseMapper.mapToExpenseDto;

@CrossOrigin
@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/getExpense/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int expenseId) {
        Expense expense = expenseService.getExpenseById(expenseId);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ExpenseDto>> listExpenses() {
        List<ExpenseDto> expenses = expenseService.findAllExpenses();
        return ResponseEntity.ok(expenses);
    }
    @PostMapping("/saveWithCategory/{categoryId}")
    public ResponseEntity<ExpenseDto> saveExpenseWithCategory(@PathVariable int categoryId, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto savedExpense = expenseService.saveExpenseWithCategory(categoryId, expenseDto);
        return ResponseEntity.ok(savedExpense);
    }
}
