package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.UserExpenseController;
import com.praveen.splitwise.dtos.UserExpensesResponseDto;
import com.praveen.splitwise.models.UserExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserHistoryCommand implements Command{
    private UserExpenseController userExpenseController;
    @Autowired
    public UserHistoryCommand(UserExpenseController userExpenseController) {
        this.userExpenseController = userExpenseController;
    }
    @Override
    public boolean isMatch(String command){
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() == 2 && commandList.get(1).equalsIgnoreCase(CommandKeyword.HISTORY);
    }
    @Override
    public void execute(String command){
        String[] commands = command.split(" ");
        Long userId = Long.parseLong(commands[0]);
        UserExpensesResponseDto userExpensesResponseDto= userExpenseController.getUserHistory(userId);
        System.out.println(userExpensesResponseDto.getMessage());
        if(userExpensesResponseDto.getStatusCode() == 200) {
            for(int i=0;i<userExpensesResponseDto.getUserExpenses().size();i++){
                UserExpense userExpense = userExpensesResponseDto.getUserExpenses().get(i);
                System.out.print("Expense Id: "+userExpense.getExpense().getId()+"\t");
                System.out.print("Paid by: "+userExpense.getExpense().getDescription()+"\t");
                System.out.print("Amount: "+userExpense.getAmount()+"\t");
                System.out.println("expenseType: "+userExpense.getUserExpenseType());
            }
        }
    }
}
