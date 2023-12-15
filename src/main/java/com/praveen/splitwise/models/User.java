package com.praveen.splitwise.models;

import java.util.List;

public class User extends  BaseModel{
    private String name;
    private String phoneNumber;
    private String password;
    List<Group> groups;
}
