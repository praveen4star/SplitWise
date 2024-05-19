package com.praveen.splitwise.services;

import com.praveen.splitwise.models.UserExpense;
import com.praveen.splitwise.repositories.UserExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserExpenseService {
    private final UserExpenseRepository userExpenseRepository;
    @Autowired
    public UserExpenseService(UserExpenseRepository userExpenseRepository){
        this.userExpenseRepository = userExpenseRepository;
    }
    public int getUserTotalAmount(Long userId){
        return userExpenseRepository.getUserTotalAmount(userId);
    }
    public List<UserExpense> getUserExpenses(Long userId){
        return userExpenseRepository.getUserExpenseByUserId(userId);
    }
}
