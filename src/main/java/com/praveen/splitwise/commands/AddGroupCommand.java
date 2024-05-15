package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.GroupController;
import com.praveen.splitwise.dtos.AddGroupRequestDto;
import com.praveen.splitwise.dtos.AddGroupResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddGroupCommand implements Command{
    private final GroupController groupController;
    @Autowired
    public AddGroupCommand(GroupController groupController) {
        this.groupController = groupController;
    }
    /* u1 AddGroup Roommates */
    @Override
    public boolean isMatch(String command) {
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() == 3 && commandList.get(1).equalsIgnoreCase(CommandKeyword.ADD_GROUP);
    }

    @Override
    public void execute(String command) {
        List<String> commandList = List.of(command.split(" "));
        String groupName = commandList.get(2);
        Long userId = Long.parseLong(commandList.get(0));
        AddGroupRequestDto addGroupRequestDto = new AddGroupRequestDto();
        addGroupRequestDto.setGroupName(groupName);
        addGroupRequestDto.setUserId(userId);
        AddGroupResponseDto addGroupResponseDto = groupController.addGroup(addGroupRequestDto);
        System.out.println(addGroupResponseDto.getMessage());
    }
}
