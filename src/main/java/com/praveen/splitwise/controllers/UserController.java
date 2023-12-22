package com.praveen.splitwise.controllers;

import com.praveen.splitwise.dtos.UserRequestDtos;
import com.praveen.splitwise.dtos.UserResponseDto;
import com.praveen.splitwise.exceptions.UserAlreadyExistsException;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.services.UserServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserServies userServies;
    @Autowired
    public UserController(UserServies userServies){
        this.userServies = userServies;
    }
    public UserResponseDto resgisterUser(UserRequestDtos userRequestDtos){
        UserResponseDto userResponseDto = new UserResponseDto();
        try {
            User user = userServies.registerUser(userRequestDtos.getName(), userRequestDtos.getPhoneNumber(), userRequestDtos.getPassword());
            userResponseDto.setUserId(user.getId());
            userResponseDto.setStatusCode(200);
            userResponseDto.setMessage("User registered successfully");
        }catch (UserAlreadyExistsException e) {
            userResponseDto.setStatusCode(500);
            userResponseDto.setMessage(e.getMessage());
        }
        return userResponseDto;
    }
}
