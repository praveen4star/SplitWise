package com.praveen.splitwise.controllers;

import com.praveen.splitwise.dtos.ExpenseRequestDto;
import com.praveen.splitwise.dtos.ExpenseResponseDto;
import com.praveen.splitwise.dtos.GroupExpenseRequestDto;
import com.praveen.splitwise.models.Expense;
import com.praveen.splitwise.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExpenseController {
    private final ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    public ExpenseResponseDto addGroupExpense(GroupExpenseRequestDto groupExpenseRequestDto) {
        ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();
        try{
            Expense expense = expenseService.saveGroupExpense(
                    groupExpenseRequestDto.getCreatedBy(),
                    groupExpenseRequestDto.getGroupId(),
                    groupExpenseRequestDto.getAmount(),
                    groupExpenseRequestDto.getPaidBy(),
                    groupExpenseRequestDto.getDescription()
            );
            expenseResponseDto.setExpense(expense);
            expenseResponseDto.setStatusCode(200);
            expenseResponseDto.setMessage("Expense added successfully");
        }
        catch (Exception e) {
            expenseResponseDto.setStatusCode(400);
            expenseResponseDto.setMessage(e.getMessage());
        }
        return expenseResponseDto;
    }
    public ExpenseResponseDto addExpense(ExpenseRequestDto expenseRequestDto){
        ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();
        try{
            Expense expense = expenseService.saveExpense(
                    expenseRequestDto.getCreatedBy(),
                    expenseRequestDto.getUserIds(),
                    expenseRequestDto.getPaidBy(),
                    expenseRequestDto.getPaidAmounts(),
                    expenseRequestDto.getSplitFactors(),
                    expenseRequestDto.getDescription(),
                    expenseRequestDto.getPayMode(),
                    expenseRequestDto.getSplitMethodType()
            );
            expenseResponseDto.setExpense(expense);
            expenseResponseDto.setStatusCode(200);
            expenseResponseDto.setMessage("Expense added successfully");
        }
        catch (Exception e) {
            expenseResponseDto.setStatusCode(500);
            expenseResponseDto.setMessage(e.getMessage());
        }
        return expenseResponseDto;
    }
}
