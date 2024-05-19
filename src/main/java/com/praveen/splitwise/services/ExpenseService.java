package com.praveen.splitwise.services;

import com.praveen.splitwise.exceptions.GroupNotFoundException;
import com.praveen.splitwise.exceptions.InvalidGroupQueries;
import com.praveen.splitwise.exceptions.UserNotFoundException;
import com.praveen.splitwise.models.*;
import com.praveen.splitwise.models.constants.ExpenseType;
import com.praveen.splitwise.models.UserExpense;
import com.praveen.splitwise.models.constants.UserExpenseType;
import com.praveen.splitwise.repositories.ExpenseRepository;
import com.praveen.splitwise.repositories.GroupRepository;
import com.praveen.splitwise.repositories.UserExpenseRepository;
import com.praveen.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserExpenseRepository userExpenseRepository;
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, GroupRepository groupRepository, UserRepository userRepository, UserExpenseRepository userExpenseRepository){
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;

        this.userExpenseRepository = userExpenseRepository;
    }
    public Expense saveGroupExpense(Long createdBy, Long groupId, int amount, Long paidBy, String description) throws UserNotFoundException, GroupNotFoundException, InvalidGroupQueries {
        Optional< User> userOptional = userRepository.findById(createdBy);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found "+createdBy);
        }
        User user = userOptional.get();
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if(groupOptional.isEmpty()){
            throw new GroupNotFoundException("Group not found "+groupId);
        }
        Group group = groupOptional.get();
        List<User> members = group.getMembers();
        if(members.stream().noneMatch(member -> member.getId().equals(createdBy))){
            throw new InvalidGroupQueries("User not part of the group");
        }

        /* creating the expense */
        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setExpenseType(ExpenseType.EXPENSE);
        expense.setGroup(group);
        expense.setDescription(description);
        expense.setCreatedBy(user);
        expense =  expenseRepository.save(expense);
        /* saving the expense for paid user */
        UserExpense userExpense = new UserExpense();
        userExpense.setExpense(expense);
        userExpense.setAmount(amount);
        userExpense.setUser(user);
        userExpense.setUserExpenseType(UserExpenseType.PAID);
        userExpenseRepository.save(userExpense);


        int share = amount/members.size();
        for(int i = 0; i<members.size(); i++){
            /* saving the expense for owe user */
            User member = members.get(i);
            UserExpense owedUserExpense = new UserExpense();
            owedUserExpense.setExpense(expense);
            owedUserExpense.setAmount(i == members.size()-1 ? amount - share*(members.size()-1) : share);
            owedUserExpense.setUser(member);
            owedUserExpense.setUserExpenseType(UserExpenseType.HAD_TO_PAY);
            userExpenseRepository.save(owedUserExpense);
        }
        return expense;
    }
}
