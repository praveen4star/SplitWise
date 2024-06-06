package com.praveen.splitwise.strategies.settleMentStrategies;

import com.praveen.splitwise.dtos.TransactionResponseDto;
import com.praveen.splitwise.models.UserExpense;

import java.util.List;

public interface Settlement {
    List<TransactionResponseDto>  settle(List<UserExpense> userExpense);
}
