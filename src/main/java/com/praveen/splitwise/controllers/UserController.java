package com.praveen.splitwise.controllers;

import com.praveen.splitwise.dtos.UpdateUserRequestDto;
import com.praveen.splitwise.dtos.UserRequestDto;
import com.praveen.splitwise.dtos.UserResponseDto;
import com.praveen.splitwise.exceptions.UserAlreadyExistsException;
import com.praveen.splitwise.exceptions.UserNotFoundException;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userServies;
    @Autowired
    public UserController(UserService userServes){
        this.userServies = userServes;
    }
    public UserResponseDto registerUser(UserRequestDto userRequestDtos){
        UserResponseDto userResponseDto = new UserResponseDto();
        try {
            User user = userServies.registerUser(userRequestDtos.getName(), userRequestDtos.getPhoneNumber(), userRequestDtos.getPassword());
            userResponseDto.setUserId(user.getId());
            userResponseDto.setStatusCode(200);
            userResponseDto.setMessage("User registered successfully");
        }catch (UserAlreadyExistsException e) {
            userResponseDto.setStatusCode(400);
            userResponseDto.setMessage(e.getMessage());
        }
        return userResponseDto;
    }
    public UserResponseDto updateUser(UpdateUserRequestDto updateUserRequestDto){
        UserResponseDto userResponseDto = new UserResponseDto();
        try{
            User user = userServies.updateUser(updateUserRequestDto.getUserId(), updateUserRequestDto.getPassword());
            userResponseDto.setUserId(user.getId());
            userResponseDto.setStatusCode(200);
            userResponseDto.setMessage("User updated successfully");
        }
        catch (UserNotFoundException e){
            userResponseDto.setStatusCode(400);
            userResponseDto.setMessage(e.getMessage());
        }
        return userResponseDto;
    }

}
