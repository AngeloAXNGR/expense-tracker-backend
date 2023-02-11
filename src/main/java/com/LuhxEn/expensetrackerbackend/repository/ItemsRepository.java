package com.LuhxEn.expensetrackerbackend.repository;

import com.LuhxEn.expensetrackerbackend.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {
}
