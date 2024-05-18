package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.UserController;
import com.praveen.splitwise.dtos.UserGroupRequestDto;
import com.praveen.splitwise.dtos.UserGroupResponseDto;
import com.praveen.splitwise.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGroupCommand implements Command{
    private final UserController userController;
    @Autowired
    public UserGroupCommand(UserController userController) {
        this.userController = userController;
    }
    //u1 Groups
    @Override
    public boolean isMatch(String command) {
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() == 2 && commandList.get(1).equalsIgnoreCase(CommandKeyword.GET_USER_GROUPS);
    }

    @Override
    public void execute(String command) {
        List<String> commandList = List.of(command.split(" "));
        UserGroupRequestDto userGroupRequestDto = new UserGroupRequestDto();
        userGroupRequestDto.setUserId(Long.parseLong(commandList.get(0)));
        UserGroupResponseDto userGroupResponseDto = userController.getUserGroup(userGroupRequestDto);
        System.out.println(userGroupResponseDto.getMessage());
        if(userGroupResponseDto.getStatusCode() == 200){
            List<Group> groups = userGroupResponseDto.getUserGroups();
            for(Group group: groups) {
                System.out.println(group.getName() + " ");
            }
        }
    }
}
