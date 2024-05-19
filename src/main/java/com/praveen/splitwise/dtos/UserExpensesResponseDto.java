package com.praveen.splitwise.dtos;

import com.praveen.splitwise.models.UserExpense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserExpensesResponseDto extends BaseResponseDto{
    private List<UserExpense> userExpenses;
}
