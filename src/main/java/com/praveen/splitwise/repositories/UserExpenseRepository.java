package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    UserExpense save(UserExpenseRepository userExpense);
}
