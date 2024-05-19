package com.praveen.splitwise.controllers;

import com.praveen.splitwise.dtos.UserExpensesResponseDto;
import com.praveen.splitwise.dtos.UserTotalAmountResponseDto;
import com.praveen.splitwise.services.UserExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExpenseController {
    private final UserExpenseService userExpenseService;
    @Autowired
    public UserExpenseController(UserExpenseService userExpenseService) {
        this.userExpenseService = userExpenseService;
    }

    public UserTotalAmountResponseDto getUserTotalAmount(Long userId) {
        UserTotalAmountResponseDto userTotalAmountResponseDto = new UserTotalAmountResponseDto();
        try{
            int totalAmount = userExpenseService.getUserTotalAmount(userId);
            userTotalAmountResponseDto.setTotalAmount(totalAmount);
            userTotalAmountResponseDto.setStatusCode(200);
            userTotalAmountResponseDto.setMessage("Total amount fetched successfully");
        }
        catch (Exception e) {
            userTotalAmountResponseDto.setStatusCode(500);
            userTotalAmountResponseDto.setMessage(e.getMessage());
        }
        return userTotalAmountResponseDto;
    }
    public UserExpensesResponseDto getUserHistory(Long userId) {
        UserExpensesResponseDto userExpensesResponseDto = new UserExpensesResponseDto();
        try{
            userExpensesResponseDto.setUserExpenses(userExpenseService.getUserExpenses(userId));
            userExpensesResponseDto.setStatusCode(200);
            userExpensesResponseDto.setMessage("User expenses fetched successfully");
        }
        catch (Exception e) {
            userExpensesResponseDto.setStatusCode(500);
            userExpensesResponseDto.setMessage(e.getMessage());
        }
        return userExpensesResponseDto;
    }
}
