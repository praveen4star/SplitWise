package com.praveen.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDto {
    private String message;
    private Integer statusCode;
}
