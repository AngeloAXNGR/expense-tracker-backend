package com.LuhxEn.expensetrackerbackend.repository;

import com.LuhxEn.expensetrackerbackend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
}
