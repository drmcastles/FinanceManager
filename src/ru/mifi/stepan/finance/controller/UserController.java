package ru.mifi.stepan.finance.controller;

import ru.mifi.stepan.finance.model.User;
import ru.mifi.stepan.finance.model.FinanceManager;

public class UserController {
    private final FinanceManager financeManager;

    public UserController(FinanceManager financeManager) {
        this.financeManager = financeManager;
    }

    public boolean registerUser(String username, String password) {
        return financeManager.registerUser(username, password);
    }

    public User loginUser(String username, String password) {
        return financeManager.loginUser(username, password);
    }

    public void addIncome(User user, String category, Double amount) {
        financeManager.addIncome(user, category, amount);  // Делегируем добавление дохода в FinanceManager
    }

    public void addExpense(User user, String category, Double amount) {
        financeManager.addExpense(user, category, amount);  // Делегируем добавление расхода в FinanceManager
    }

    public void setCategoryBudget(User user, String category, Double amount) {
        financeManager.setCategoryBudget(user, category, amount);  // Делегируем установку бюджета в FinanceManager
    }

    public void printSummary(User user) {
        financeManager.printSummary(user);  // Делегируем вывод сводной информации в FinanceManager
    }

    public void saveData() {
        financeManager.saveData();  // Сохраняем данные в файл
    }

    public void loadData() {
        financeManager.loadData();  // Загружаем данные из файла
    }
}
