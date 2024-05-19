package com.praveen.splitwise.commands;

import com.praveen.splitwise.controllers.ExpenseController;
import com.praveen.splitwise.dtos.ExpenseRequestDto;
import com.praveen.splitwise.dtos.ExpenseResponseDto;
import com.praveen.splitwise.models.constants.PayMode;
import com.praveen.splitwise.models.constants.SplitMethodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AddExpenseCommand implements Command{
    /*
        1. 2 Expense 1 52 iPay 1000 Equal Desc Last Night dinner
        2. 1 Expense 2 52 iPay 1000 Percent 20 30 50 Desc House rent
        3. 52 Expense 1 2 iPay 1000 Ratio 5 2 3 Desc Goa trip
        4. 52 Expense 1 2 iPay 1000 Exact 100 300 600 Desc Groceries
        5. u1 Expense u2 u3 MultiPay 100 300 200 Equal Desc Lunch at office
        6. u1 Expense u2 u3 MultiPay 500 300 200 Percent 20 30 50 Desc Netflix subscription

     */
    private final ExpenseController expenseController;
    @Autowired
    public AddExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }
    @Override
    public boolean isMatch(String cmd) {
        List<String> commandList = List.of(cmd.split(" "));
        return commandList.get(1).equalsIgnoreCase(CommandKeyword.EXPENSE);
    }
    @Override
    public void execute(String cmd) {
        List<String> commandList = List.of(cmd.split(" "));
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        expenseRequestDto.setCreatedBy(Long.parseLong(commandList.get(0)));
        List<Long> userIds = new ArrayList<>();
        int idx = 2;
        while (!commandList.get(idx).equalsIgnoreCase(String.valueOf(PayMode.IPAY)) && !commandList.get(idx).equalsIgnoreCase(String.valueOf(PayMode.MULTIPAY))) {
            userIds.add(Long.parseLong(commandList.get(idx)));
            idx++;
        }
        if (commandList.get(idx).equalsIgnoreCase(String.valueOf(PayMode.IPAY))) {
            userIds.add(Long.parseLong(commandList.get(0)));
            expenseRequestDto.setUserIds(userIds);
            expenseRequestDto.setPaidBy(List.of(Long.parseLong(commandList.get(0))));
            expenseRequestDto.setPayMode(PayMode.IPAY);
            expenseRequestDto.setPaidAmounts(List.of(Integer.parseInt(commandList.get(idx + 1))));
            expenseRequestDto.setSplitMethodType(getSplitMethodType(commandList.get(idx + 2)));
            List<Integer> splitFactors = new ArrayList<>();
            idx = idx + 3;
            while(!commandList.get(idx).equalsIgnoreCase("Desc")) {
                splitFactors.add(Integer.parseInt(commandList.get(idx)));
                idx++;
            }
            expenseRequestDto.setSplitFactors(splitFactors);
            expenseRequestDto.setDescription(commandList.subList(idx+1, commandList.size()).stream().reduce((s, s2) -> s + " " + s2).get());

        } else {
            expenseRequestDto.setPayMode(PayMode.MULTIPAY);
            userIds.add(Long.parseLong(commandList.get(0)));
            expenseRequestDto.setUserIds(userIds);
            expenseRequestDto.setPaidBy(userIds);
            List<Integer> paidAmounts = new ArrayList<>();
            idx = idx + 1;
            while(!containsIgnoreCase(new String[]{"Equal", "Percent", "Ratio", "Exact"}, commandList.get(idx))) {
                paidAmounts.add(Integer.parseInt(commandList.get(idx)));
                idx++;
            }
            expenseRequestDto.setPaidAmounts(paidAmounts);
            expenseRequestDto.setSplitMethodType(getSplitMethodType(commandList.get(idx)));
            List<Integer> splitFactors = new ArrayList<>();
            idx = idx + 1;
            while(!commandList.get(idx).equalsIgnoreCase("Desc")) {
                splitFactors.add(Integer.parseInt(commandList.get(idx)));
                idx++;
            }
            expenseRequestDto.setSplitFactors(splitFactors);
            expenseRequestDto.setDescription(commandList.subList(idx+1, commandList.size()).stream().reduce((s, s2) -> s + " " + s2).get());
        }
        ExpenseResponseDto expenseResponseDto = expenseController.addExpense(expenseRequestDto);
        System.out.println(expenseResponseDto.getMessage());
        if(expenseResponseDto.getStatusCode() == 200){
            System.out.println("Expense added successfully with id: " + expenseResponseDto.getExpense().getId());
        }

    }
    private SplitMethodType getSplitMethodType(String splitMethod) {
        if (splitMethod.equalsIgnoreCase("Equal")) {
            return SplitMethodType.EQUAL;
        } else if (splitMethod.equalsIgnoreCase("Percent")) {
            return SplitMethodType.PERCENT;
        } else if (splitMethod.equalsIgnoreCase("Ratio")) {
            return SplitMethodType.RATIO;
        } else if (splitMethod.equalsIgnoreCase("Exact")) {
            return SplitMethodType.EXACT;
        }
        return null;
    }
    private boolean containsIgnoreCase(String str[], String subString) {
        return Arrays.stream(str).anyMatch(s -> s.equalsIgnoreCase(subString));
    }
}
