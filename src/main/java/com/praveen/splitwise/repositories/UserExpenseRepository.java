package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    UserExpense save(UserExpenseRepository userExpense);
    @Query("SELECT SUM(CASE WHEN t.userExpenseType = 0 THEN t.amount ELSE -t.amount END) FROM UserExpense t WHERE t.user.id = :userId")
    int getUserTotalAmount(@Param("userId") Long userId);
    List<UserExpense> getUserExpenseByUserId(Long userId);
    Optional<List<UserExpense>> findAllByExpenseId(Long expenseId);
}
