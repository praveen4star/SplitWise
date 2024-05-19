package com.praveen.splitwise.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {
    private  final List<Command> commands ;
    @Autowired
    public CommandRegistry(
            RegisterUserCommand registerCommand,
            UpdateUserCommand updateCommand,
            AddGroupCommand addGroupCommand,
            AddGroupMember addGroupMember,
            UserGroupCommand userGroupCommand,
            GroupExpenseCommand groupExpenseCommand,
            AddExpense addExpense
    ){
        commands = new ArrayList<>();
        commands.add(registerCommand);
        commands.add(updateCommand);
        commands.add(addGroupCommand);
        commands.add(addGroupMember);
        commands.add(userGroupCommand);
        commands.add(groupExpenseCommand);
        commands.add(addExpense);
    }
    public void execute(String commandName){
        boolean isMatch = false;
        for(Command command: commands){
            if(command.isMatch(commandName)){
                isMatch = true;
                command.execute(commandName);
            }
        }
        if(!isMatch){
            System.out.println("Invalid Command");
        }

    }
}
