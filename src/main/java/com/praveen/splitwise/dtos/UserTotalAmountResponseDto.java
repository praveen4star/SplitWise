package com.praveen.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTotalAmountResponseDto extends UserResponseDto{
    private int totalAmount;
}
