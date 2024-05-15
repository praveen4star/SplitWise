package com.praveen.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
    @ManyToMany
    private List<User> members;
    @ManyToOne
    private User createdBy;
    public void addMember(User user) {
        if(members == null){
            members = new ArrayList<>();
        }
        members.add(user);
    }
}
