package com.expense.tracker.service;

import com.expense.tracker.dto.ExpenseDTO;
import com.expense.tracker.entity.Expense;
import com.expense.tracker.exception.ResourceNotFoundException;
import com.expense.tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense saveExpense(ExpenseDTO dto) {
        Expense expense=new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        return repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }
    public Expense getExpenseById(long id){
        return repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Expense Not Found"));
    }

    public void deleteExpense(long id) {
        repository.deleteById(id);
    }
    public Expense updateExpense(long id, Expense updated) {
        Expense existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        existing.setTitle(updated.getTitle());
        existing.setAmount(updated.getAmount());
        existing.setCategory(updated.getCategory());

        return repository.save(existing);
    }
}
