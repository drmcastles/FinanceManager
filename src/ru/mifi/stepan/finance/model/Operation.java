package ru.mifi.stepan.finance.model;

import java.time.LocalDate;

public class Operation {
    private Category category;
    private double amount;
    private OperationType operationType;
    private LocalDate date;

    public Operation(Category category, double amount, OperationType operationType, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.operationType = operationType;
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public LocalDate getDate() {
        return date;
    }
}
