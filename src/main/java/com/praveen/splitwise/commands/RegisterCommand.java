package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.UserController;
import com.praveen.splitwise.dtos.UserRequestDto;
import com.praveen.splitwise.dtos.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RegisterCommand implements Command{
    private UserController userController;
    public RegisterCommand(UserController userController){
        this.userController = userController;
    }
    public boolean isMatch(String command){
        List<String> commandList = List.of(command.split(" "));
        if(commandList.size() == 4 && commandList.get(0).equalsIgnoreCase(CommandKeyword.REGISTER)){
            return true;
        }
        return false;
    }
    public void execute(String command){
        List<String> commandList = List.of(command.split(" "));
        UserRequestDto userRequestDtos = new UserRequestDto();
        userRequestDtos.setName(commandList.get(1));
        userRequestDtos.setPhoneNumber(commandList.get(2));
        userRequestDtos.setPassword(commandList.get(3));
        UserResponseDto userResponseDto=  userController.registerUser(userRequestDtos);
        System.out.println(userResponseDto.getMessage());
    }
}
