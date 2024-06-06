package com.praveen.splitwise.strategies.settleMentStrategies;

import com.praveen.splitwise.dtos.TransactionResponseDto;
import com.praveen.splitwise.models.User;
import com.praveen.splitwise.models.UserExpense;
import com.praveen.splitwise.models.constants.UserExpenseType;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class PriorityQueueStrategy implements Settlement{
    public List<TransactionResponseDto> settle(List<UserExpense> userExpenses) {
        Map<Long, Pair<User, Integer>> userAmountMap = new HashMap<>();

        for(UserExpense userExpense: userExpenses){
            int flag = userExpense.getUserExpenseType() == UserExpenseType.PAID ? 1 : -1;
            if(userAmountMap.containsKey(userExpense.getUser().getId())){
                userAmountMap.computeIfPresent(userExpense.getUser().getId(), (k, userAmount) -> new Pair<>(userAmount.a, userAmount.b + flag * userExpense.getAmount()));
            }
            else{
                userAmountMap.put(userExpense.getUser().getId(), new Pair<>(userExpense.getUser(), flag * userExpense.getAmount()));
            }
        }
        PriorityQueue<Pair<User, Integer>> paidUsers = new PriorityQueue<>((a, b) -> b.b - a.b);
        PriorityQueue<Pair<User, Integer>> owedUsers = new PriorityQueue<>((a, b) -> a.b - b.b);

        for(Map.Entry<Long, Pair<User, Integer>> entry: userAmountMap.entrySet()) {
            if (entry.getValue().b > 0) {
                paidUsers.add(entry.getValue());
            } else if (entry.getValue().b < 0) {
                owedUsers.add(new Pair<>(entry.getValue().a, -entry.getValue().b));
            }
        }
        List<TransactionResponseDto> transactionResponseDtos = new ArrayList<>();

        while(!paidUsers.isEmpty()){
            Pair<User, Integer> paidUser = paidUsers.poll();
            Pair<User, Integer> owedUser = owedUsers.poll();
            TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
            /*
            *  paidUser.getAmount() >= owedUser.getAmount() -> paidUser.getAmount() - owedUser.getAmount() -> owedUser.setAmount(0)
            * paidUser.getAmount() < owedUser.getAmount() -> owedUser.getAmount() - paidUser.getAmount() -> paidUser.setAmount(0)
            * */

            transactionResponseDto.setToUserName(paidUser.a.getName());
            transactionResponseDto.setFromUserName(owedUser.a.getName());
            transactionResponseDto.setAmount(Math.min(paidUser.b, owedUser.b));
            if(paidUser.b > owedUser.b){
                paidUsers.add(new Pair<>(paidUser.a,  paidUser.b - owedUser.b));
            }
            else if(paidUser.b < owedUser.b){
                paidUsers.add(new Pair<>(owedUser.a,  owedUser.b - paidUser.b));
            }
            transactionResponseDtos.add(transactionResponseDto);
        }
        return transactionResponseDtos;
    }
}
