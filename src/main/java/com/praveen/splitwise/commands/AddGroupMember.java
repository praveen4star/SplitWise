package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.GroupController;
import com.praveen.splitwise.dtos.AddGroupMemberRequestDto;
import com.praveen.splitwise.dtos.GroupResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddGroupMember implements Command{
    private final GroupController groupController;
    @Autowired
    public AddGroupMember(GroupController groupController) {
        this.groupController = groupController;
    }
    // cmd : u1 AddMember g1 u2
    @Override
    public boolean isMatch(String command) {
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() == 4 && commandList.get(1).equalsIgnoreCase(CommandKeyword.ADD_GROUP_MEMBER);
    }

    @Override
    public void execute(String command) {
        List<String> commandList = List.of(command.split(" "));
        Long addedByUserId = Long.parseLong(commandList.get(0));
        Long addedUserId = Long.parseLong(commandList.get(3));
        Long groupId = Long.parseLong(commandList.get(2));
        AddGroupMemberRequestDto addGroupMemberRequestDto = new AddGroupMemberRequestDto();
        addGroupMemberRequestDto.setAddedByUserId(addedByUserId);
        addGroupMemberRequestDto.setUserId(addedUserId);
        addGroupMemberRequestDto.setGroupId(groupId);
        GroupResponseDto groupResponseDto = groupController.addMember(addGroupMemberRequestDto);
        System.out.println("Status of controller "+ groupResponseDto.getStatusCode() + " message : "+groupResponseDto.getMessage());
    }
}
