package com.praveen.splitwise.services;

import com.praveen.splitwise.exceptions.UserAlreadyExistsException;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.models.UserStatus;
import com.praveen.splitwise.repositories.UserRepostory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServies {
    private UserRepostory userRepostory;
    public UserServies(
            UserRepostory userRepostory
    ){
        this.userRepostory = userRepostory;
    }
    public User registerUser(String name, String phoneNumber, String password) throws UserAlreadyExistsException {

        Optional<User> optionalUser = userRepostory.findByPhoneNumber(phoneNumber);
        if(optionalUser.isPresent()){

            User user = optionalUser.get();
            if(user.getUserStatus().equals(UserStatus.ACTIVE)){
                throw new UserAlreadyExistsException("User already exists");
            }
            else{
                user.setUserStatus(UserStatus.ACTIVE);
                user.setName(name);
                user.setPassword(password);
                return userRepostory.save(user);
            }
        }

        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepostory.save(user);

    }
}
