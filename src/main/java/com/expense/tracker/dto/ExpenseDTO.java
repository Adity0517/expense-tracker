package com.expense.tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ExpenseDTO {
    @NotBlank(message = "Title is Required")
    private String title;
    @Positive(message = "Amount must be Positive")
    private double amount;
    private String category;

    public @NotBlank(message = "Title is Required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is Required") String title) {
        this.title = title;
    }

    public @Positive(message = "Amount must be Positive") double getAmount() {
        return amount;
    }

    public void setAmount(@Positive(message = "Amount must be Positive") double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
