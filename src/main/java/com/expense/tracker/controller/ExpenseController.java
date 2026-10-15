package com.expense.tracker.controller;

import com.expense.tracker.dto.ExpenseDTO;
import com.expense.tracker.entity.Expense;
import com.expense.tracker.repository.ExpenseRepository;
import com.expense.tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public Expense saveExpense(@Valid @RequestBody ExpenseDTO dto) {
        return service.saveExpense(dto);
    }

    @GetMapping
    public List<Expense> getAllExpense() {
        return service.getAllExpenses();
    }
    @GetMapping("/{id}")
    public Expense getExpenseById (@PathVariable long id){
        return service.getExpenseById(id);
     }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable long id) {
        service.deleteExpense(id);
        return "Expense deleted successfully";
    }

    @PutMapping("/{id}")
    public Expense updateExpense(
            @PathVariable long id,
            @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }
}