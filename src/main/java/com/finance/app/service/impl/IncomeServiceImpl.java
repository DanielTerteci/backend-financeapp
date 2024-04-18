package com.finance.app.service.impl;

import com.finance.app.dto.CategoryDto;
import com.finance.app.dto.IncomeDto;
import com.finance.app.mapper.CategoryMapper;
import com.finance.app.mapper.IncomeMapper;
import com.finance.app.models.Category;
import com.finance.app.models.Income;
import com.finance.app.models.Register;
import com.finance.app.repository.CategoryRepository;
import com.finance.app.repository.IncomeRepository;
import com.finance.app.repository.RegisterRepository;
import com.finance.app.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.finance.app.mapper.IncomeMapper.mapToIncomeDto;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<IncomeDto> findAllIncomes() {
        List<Income> income = incomeRepository.findAll();
        return income.stream().map((income1 -> mapToIncomeDto(income1))).collect(Collectors.toList());
    }

    @Override
    public Income getIncomeById(int incomeId) {
        return incomeRepository.findById(incomeId).orElse(null);
    }

    @Override
    public IncomeDto saveIncomeWithCategory(int categoryId, IncomeDto incomeDto) {
        Integer userId = incomeDto.getUserId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CategoryDto categoryDto = CategoryMapper.mapToCategoryDto(category);
        incomeDto.setCategory(categoryDto);

        Register user = userId != null ? registerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")) : null;

        Income newIncome = IncomeMapper.mapToIncome(incomeDto);
        newIncome.setCategory(category);

        try {
            Income savedIncome = incomeRepository.save(newIncome);
            return IncomeMapper.mapToIncomeDto(savedIncome);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save the expense", e);
        }
    }

}
