package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense save(Expense expense);
}
