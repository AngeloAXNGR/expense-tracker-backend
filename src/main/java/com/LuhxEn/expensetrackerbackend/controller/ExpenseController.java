package com.LuhxEn.expensetrackerbackend.controller;

import com.LuhxEn.expensetrackerbackend.model.Expense;
import com.LuhxEn.expensetrackerbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ExpenseController {
    @Autowired
    ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestParam(required = false) String title){
        List<Expense> expenses = new ArrayList<Expense>();
        if(title == null)
            expenseRepository.findAll().forEach(expenses::add);
        else
            expenseRepository.findByTitleContaining(title).forEach(expenses::add);

        if(expenses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") long id){
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PostMapping("/expenses")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        Expense _expense = expenseRepository.save(new Expense(expense.getTitle(), expense.getRecipient(), expense.getAllowance(),expense.getDescription()));
        return new ResponseEntity<>(_expense, HttpStatus.CREATED);
    }
}
