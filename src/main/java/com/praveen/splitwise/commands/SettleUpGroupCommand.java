package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.ExpenseController;
import com.praveen.splitwise.dtos.TransactionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SettleUpGroupCommand implements Command{
    private  ExpenseController expenseController;
    @Autowired
    public SettleUpGroupCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }
    @Override
    public boolean isMatch(String command) {
        List<String> commandList = Arrays.asList(command.split(" "));
        return commandList.size() == 3 && commandList.get(1).equalsIgnoreCase(CommandKeyword.SETTLE_UP);
    }

    @Override
    public void execute(String command) {
        List<String> commandList = Arrays.asList(command.split(" "));
        Long groupId = Long.parseLong(commandList.get(2));
        List<TransactionResponseDto> transactionResponseDtos = expenseController.settleUpGroup(groupId);
        for(TransactionResponseDto transactionResponseDto: transactionResponseDtos){
            System.out.println(transactionResponseDto.getToUserName() +" "+ transactionResponseDto.getAmount() +" "+ transactionResponseDto.getFromUserName())  ;
        }
    }
}
