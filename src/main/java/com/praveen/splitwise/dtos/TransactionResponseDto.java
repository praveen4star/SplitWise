package com.praveen.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDto {
    String fromUserName;
    String toUserName;
    int amount;
}
