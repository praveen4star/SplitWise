package com.praveen.splitwise.models;

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
