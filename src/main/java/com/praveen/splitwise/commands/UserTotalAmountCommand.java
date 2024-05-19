package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.UserExpenseController;
import com.praveen.splitwise.dtos.UserTotalAmountResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTotalAmountCommand implements Command{
    private final UserExpenseController userExpenseController;
    @Autowired
    public UserTotalAmountCommand(UserExpenseController userExpenseController) {
        this.userExpenseController = userExpenseController;
    }
    @Override
    public boolean isMatch(String command) {
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() == 2 && commandList.get(1).equalsIgnoreCase(CommandKeyword.MY_TOTAL);
    }

    @Override
    public void execute(String command) {
        String[] commands = command.split(" ");
        Long userId = Long.parseLong(commands[0]);
        UserTotalAmountResponseDto userTotalAmountResponseDto = userExpenseController.getUserTotalAmount(userId);
        System.out.println(userTotalAmountResponseDto.getMessage());
        if(userTotalAmountResponseDto.getStatusCode() == 200) {
            System.out.println("Total amount: " + userTotalAmountResponseDto.getTotalAmount());
        }
    }
}
