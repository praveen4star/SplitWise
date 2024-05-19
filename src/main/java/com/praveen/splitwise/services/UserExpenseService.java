package com.praveen.splitwise.services;

import com.praveen.splitwise.repositories.UserExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
