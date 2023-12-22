package com.praveen.splitwise.commands;

public interface Command {
    public boolean isMatch(String command);
    public void execute(String command);
}
