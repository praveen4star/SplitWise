package com.praveen.splitwise.services;

import com.praveen.splitwise.exceptions.UserNotFoundException;
import com.praveen.splitwise.models.Group;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.repositories.GroupRepository;
import com.praveen.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        group.addMember(user);
        return groupRepository.save(group);
    }
}
