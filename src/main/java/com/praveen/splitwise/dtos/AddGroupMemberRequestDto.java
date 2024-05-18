package com.praveen.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupMemberRequestDto {
    private Long groupId;
    private Long userId;
    private Long addedByUserId;
}
