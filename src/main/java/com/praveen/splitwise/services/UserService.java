package com.praveen.splitwise.services;

import com.praveen.splitwise.exceptions.UserAlreadyExistsException;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.models.UserStatus;
import com.praveen.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }
    public User registerUser(String name, String phoneNumber, String password) throws UserAlreadyExistsException {

        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isPresent()){

            User user = optionalUser.get();
            if(user.getUserStatus().equals(UserStatus.ACTIVE)){
                throw new UserAlreadyExistsException("User already exists "+"phoneNumber: "+phoneNumber);
            }
            else{
                user.setUserStatus(UserStatus.ACTIVE);
                user.setName(name);
                user.setPassword(password);
                return userRepository.save(user);
            }
        }

        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user);

    }
}
