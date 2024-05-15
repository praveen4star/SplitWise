package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.UserController;
import com.praveen.splitwise.dtos.UpdateUserRequestDto;
import com.praveen.splitwise.dtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateUserCommand implements Command{
    //cmd : u1 UpdateProfile robinchwan
    private UserController userController;
    @Autowired
    public UpdateUserCommand(UserController userController){
        this.userController = userController;
    }
    public boolean isMatch(String command){
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() == 3 && commandList.get(1).equalsIgnoreCase(CommandKeyword.UPDATE_PROFILE);
    }
    public void execute(String command){
        List<String> commandList = List.of(command.split(" "));
        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto();
        updateUserRequestDto.setUserId(Long.parseLong(commandList.get(0)));
        updateUserRequestDto.setPassword(commandList.get(2));
        UserResponseDto userResponseDto = userController.updateUser(updateUserRequestDto);
        System.out.println(userResponseDto.getMessage());
    }
}
