package com.praveen.splitwise.dtos;

import com.praveen.splitwise.models.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserGroupResponseDto extends BaseResponseDto{
    private List<Group> userGroups;
}
