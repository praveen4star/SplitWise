package com.praveen.splitwise.models;

import java.util.List;

public class Group extends BaseModel{
    private String name;
    private String description;
    private List<User> members;
    private User createdBy;

}
