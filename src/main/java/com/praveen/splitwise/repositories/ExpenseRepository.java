package com.praveen.splitwise.repositories;

import com.praveen.splitwise.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense save(Expense expense);
    Optional<List<Expense>> findAllByGroupId(Long groupId);
}
