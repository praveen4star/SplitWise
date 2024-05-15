package com.praveen.splitwise.controllers;

import com.praveen.splitwise.dtos.AddGroupRequestDto;
import com.praveen.splitwise.dtos.AddGroupResponseDto;
import com.praveen.splitwise.exceptions.UserNotFoundException;
import com.praveen.splitwise.models.Group;
import com.praveen.splitwise.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupController {
    private final GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    public AddGroupResponseDto addGroup(AddGroupRequestDto addGroupRequestDto) {
        AddGroupResponseDto addGroupResponseDto = new AddGroupResponseDto();
        try{
            Group group = groupService.saveGroup(addGroupRequestDto.getUserId(), addGroupRequestDto.getGroupName());
            addGroupResponseDto.setGroupId(group.getId());
            addGroupResponseDto.setStatusCode(200);
            addGroupResponseDto.setMessage("Group added successfully");
        }
        catch (UserNotFoundException e){
            addGroupResponseDto.setStatusCode(400);
            addGroupResponseDto.setMessage(e.getMessage());
        }
        return addGroupResponseDto;
    }

}
