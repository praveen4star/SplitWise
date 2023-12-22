package com.praveen.splitwise.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String userAlreadyExists) {
        super(userAlreadyExists);
    }
}
