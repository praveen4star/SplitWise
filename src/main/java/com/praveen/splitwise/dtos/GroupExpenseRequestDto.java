package com.praveen.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupExpenseRequestDto {
    private Long createdBy;
    private Long groupId;
    private Long paidBy;
    private int amount;
    private String description;
}
