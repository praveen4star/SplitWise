package com.praveen.splitwise.models;

import com.praveen.splitwise.models.constants.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends  BaseModel{
    private String name;
    private String phoneNumber;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "members")
    List<Group> groups;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;
}
