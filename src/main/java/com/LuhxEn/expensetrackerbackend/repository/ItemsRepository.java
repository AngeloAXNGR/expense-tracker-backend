package com.LuhxEn.expensetrackerbackend.repository;

import com.LuhxEn.expensetrackerbackend.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    List<Items> findByExpenseId(long expenseId);
}
