package com.praveen.splitwise.dtos;

import com.praveen.splitwise.models.constants.PayMode;
import com.praveen.splitwise.models.constants.SplitMethodType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpenseRequestDto {
    private List<Long> userIds; // users involved in the expense
    private List<Long> paidBy;
    private Long createdBy;
    private List<Integer> paidAmounts;
    private String description;
    private List<Integer> splitFactors;
    private SplitMethodType splitMethodType;
    private PayMode payMode;
}
