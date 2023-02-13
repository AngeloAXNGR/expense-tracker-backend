package com.LuhxEn.expensetrackerbackend.controller;

import com.LuhxEn.expensetrackerbackend.model.Items;
import com.LuhxEn.expensetrackerbackend.repository.ExpenseRepository;
import com.LuhxEn.expensetrackerbackend.repository.ItemsRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItemsController {
    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    ItemsRepository itemsRepository;

    @GetMapping("/expenses/{expenseId}/items")
    public ResponseEntity<List<Items>> getAllItemsByExpenseId(@PathVariable(value="expenseId") long expenseId){
        if(!expenseRepository.existsById(expenseId)){
            throw new RuntimeException();
        }

        List<Items> items = itemsRepository.findByExpenseId(expenseId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable("id") long id){
        Items item = itemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/expenses/{expenseId}/items")
    public ResponseEntity<Items> createItems(@PathVariable(value="expenseId") long expenseId, @RequestBody Items itemRequest){
        Items item = expenseRepository.findById(expenseId).map(expense -> {
            itemRequest.setExpense(expense); // Associating created item to a specific expense FK which is expenseId
            return itemsRepository.save(itemRequest);
        }).orElseThrow(() -> new RuntimeException());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable("id") long id, @RequestBody Items itemRequest){
        Items item = itemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setQuantity(itemRequest.getQuantity());

        return new ResponseEntity<>(itemsRepository.save(item), HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Items> deleteItem(@PathVariable("id") long id){
        itemsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
