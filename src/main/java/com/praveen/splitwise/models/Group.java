package com.praveen.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "groups")
public class Group extends BaseModel{
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> members;
    @ManyToOne
    private User createdBy;
}
