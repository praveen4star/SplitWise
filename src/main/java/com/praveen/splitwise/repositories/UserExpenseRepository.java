package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    UserExpense save(UserExpenseRepository userExpense);
    @Query("SELECT SUM(CASE WHEN t.userExpenseType = 0 THEN t.amount ELSE -t.amount END) FROM UserExpense t WHERE t.user.id = :userId")
    int getUserTotalAmount(@Param("userId") Long userId);
}
