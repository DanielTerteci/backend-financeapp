package com.finance.app.controller;

import com.finance.app.dto.ExpenseDto;
import com.finance.app.dto.IncomeDto;
import com.finance.app.models.Expense;
import com.finance.app.models.Income;
import com.finance.app.security.JwtTokenProvider;
import com.finance.app.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/income")
public class IncomeController {
    private IncomeService incomeService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public IncomeController(IncomeService incomeService, JwtTokenProvider jwtTokenProvider) {
        this.incomeService = incomeService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/getIncome/{incomeId}")
    public ResponseEntity<Income> getIncomeById(@PathVariable int incomeId) {
        Income income = incomeService.getIncomeById(incomeId);
        return ResponseEntity.ok(income);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<IncomeDto>> listIncomes() {
        List<IncomeDto> incomes = incomeService.findAllIncomes();
        return ResponseEntity.ok(incomes);
    }
    @PostMapping("/saveWithCategory/{categoryId}")
    public ResponseEntity<IncomeDto> saveIncomeWithCategory(@PathVariable int categoryId, @RequestBody IncomeDto incomeDto,@RequestHeader("Authorization") String token) {
        Long userId = jwtTokenProvider.getUserIdFromToken(token.split(" ")[1]);
        incomeDto.setUserId(userId.intValue());
        IncomeDto savedIncomes = incomeService.saveIncomeWithCategory(categoryId,incomeDto);
        return ResponseEntity.ok(savedIncomes);
    }


}
