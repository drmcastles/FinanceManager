package ru.mifi.stepan.finance.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Wallet implements Serializable {
    private final Map<String, Double> categoryIncomes = new HashMap<>();
    private final Map<String, Double> categoryExpenses = new HashMap<>();
    private final Map<String, Double> categoryBudgets = new HashMap<>();

    public void addIncome(String category, double amount) {
        categoryIncomes.put(category, categoryIncomes.getOrDefault(category, 0.0) + amount);
    }

    public void addExpense(String category, double amount) {
        categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + amount);
    }

    public void setCategoryBudget(String category, double amount) {
        categoryBudgets.put(category, amount);
    }

    // Получение общего дохода
    public double getTotalIncome() {
        return categoryIncomes.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Получение общего расхода
    public double getTotalExpenses() {
        return categoryExpenses.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Получение общего бюджета
    public double getTotalBudget() {
        return categoryBudgets.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Получение дохода по категории
    public double getCategoryIncome(String category) {
        return categoryIncomes.getOrDefault(category, 0.0);
    }

    // Получение расхода по категории
    public double getCategoryExpense(String category) {
        return categoryExpenses.getOrDefault(category, 0.0);
    }

    // Получение бюджета по категории
    public double getCategoryBudget(String category) {
        return categoryBudgets.getOrDefault(category, 0.0);
    }

    // Получение всех бюджетов по категориям
    public Map<String, Double> getCategoryBudgets() {
        return categoryBudgets;
    }
}
