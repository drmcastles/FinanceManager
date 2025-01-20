package ru.mifi.stepan.finance.model;

public class Category {
    private String name;
    private double budget;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
