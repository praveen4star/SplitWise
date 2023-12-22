package com.praveen.splitwise.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistory {
    private  List<Command> commands ;
    @Autowired
    public CommandRegistory( RegisterCommand registerCommand){
        commands = new ArrayList<>();
        commands.add(registerCommand);
    }
    public void execute(String commandName){
        for(Command command: commands){
            if(command.isMatch(commandName)){
                command.execute(commandName);
            }
        }
    }
}
