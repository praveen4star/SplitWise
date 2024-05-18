package com.praveen.splitwise.dtos;

import com.praveen.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseResponseDto extends BaseResponseDto{
    private Expense expense;
}
