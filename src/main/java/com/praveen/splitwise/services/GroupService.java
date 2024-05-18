package com.praveen.splitwise.services;

import com.praveen.splitwise.exceptions.GroupNotFoundException;
import com.praveen.splitwise.exceptions.InvalidGroupQueries;
import com.praveen.splitwise.exceptions.UserNotFoundException;
import com.praveen.splitwise.models.Group;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.repositories.GroupRepository;
import com.praveen.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
   @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
       this.userRepository = userRepository;
         this.groupRepository = groupRepository;
    }
    public Group saveGroup(Long userId, String groupName) throws UserNotFoundException {
        Optional<User> optionalUser =  userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found userId: "+userId);
        }
        User user = optionalUser.get();
        Group group = new Group();
        group.setCreatedBy(user);
        group.setName(groupName);
        List<User> members = group.getMembers();
        if(members == null){
            members = new ArrayList<>();
        }
        members.add(user);
        group.setMembers(members);
        return groupRepository.save(group);
    }
    public Group addMember(Long groupId, Long userId, Long addByUserId) throws UserNotFoundException, GroupNotFoundException, InvalidGroupQueries {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if(optionalGroup.isEmpty()){
            throw new GroupNotFoundException("Group not found groupId: "+groupId);
        }
        Group group = optionalGroup.get();
        if(!Objects.equals(group.getCreatedBy().getId(), addByUserId)){
            throw new InvalidGroupQueries("User not allowed to add member to group");
        }
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found userId: "+userId);
        }

        User user = optionalUser.get();
        List<User> members = group.getMembers();
        if(members == null){
            members = new ArrayList<>();
        }
        members.add(user);
        group.setMembers(members);
        return groupRepository.save(group);
    }
}
