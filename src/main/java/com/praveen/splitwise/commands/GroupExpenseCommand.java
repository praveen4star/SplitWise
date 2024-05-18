package com.praveen.splitwise.commands;


import com.praveen.splitwise.controllers.ExpenseController;
import com.praveen.splitwise.dtos.ExpenseResponseDto;
import com.praveen.splitwise.dtos.GroupExpenseRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupExpenseCommand implements Command{
    //u1 groupExpense g1 iPay 1000 Equal Desc Wifi Bill
    private final ExpenseController expensecontroller;
    @Autowired
    public GroupExpenseCommand(ExpenseController expensecontroller){
        this.expensecontroller = expensecontroller;
    }
    @Override
    public boolean isMatch(String command) {
        List<String> commandList = List.of(command.split(" "));
        return commandList.size() >= 7 && commandList.get(1).equalsIgnoreCase(CommandKeyword.GROUP_EXPENSE);
    }
    @Override
    public void execute(String command) {
        List<String> commandList = List.of(command.split(" "));
        GroupExpenseRequestDto groupExpenseRequestDto = new GroupExpenseRequestDto();
        groupExpenseRequestDto.setGroupId(Long.parseLong(commandList.get(2)));
        groupExpenseRequestDto.setAmount(Integer.parseInt(commandList.get(4)));
        groupExpenseRequestDto.setCreatedBy(Long.parseLong(commandList.get(0)));
        groupExpenseRequestDto.setPaidBy(Long.parseLong(commandList.get(0)));
        groupExpenseRequestDto.setDescription(commandList.subList(7, commandList.size()).stream().reduce((s, s2) -> s + " " + s2).get());
        ExpenseResponseDto expenseResponseDto = expensecontroller.addGroupExpense(groupExpenseRequestDto);
        System.out.println(expenseResponseDto.getMessage());

    }
}
