package com.praveen.splitwise.models;


import com.praveen.splitwise.models.BaseModel;
import com.praveen.splitwise.models.Expense;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.models.constants.UserExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExpense extends BaseModel {
    private int amount;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;
    @ManyToOne
    private Expense expense;
}
