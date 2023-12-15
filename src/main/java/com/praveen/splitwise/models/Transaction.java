package com.praveen.splitwise.models;

import java.util.List;

public class Transaction extends BaseModel{
    private User paidBy;
    private Group group;
    List<Expense> owedBy;

}
