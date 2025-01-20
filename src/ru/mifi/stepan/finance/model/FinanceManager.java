package ru.mifi.stepan.finance.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FinanceManager {
    private final Map<String, User> users = new HashMap<>();
    private User currentUser;

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;  // Логин уже существует
        }

        User user = new User(username, password);
        users.put(username, user);
        return true;
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }
        return null;  // Неверный логин или пароль
    }

    public void addIncome(User user, String category, Double amount) {
        Wallet wallet = user.getWallet();
        wallet.addIncome(category, amount);  // Добавляем доход в кошелек пользователя
    }

    public void addExpense(User user, String category, Double amount) {
        Wallet wallet = user.getWallet();
        wallet.addExpense(category, amount);  // Добавляем расход в кошелек пользователя
    }

    public void setCategoryBudget(User user, String category, Double amount) {
        Wallet wallet = user.getWallet();
        wallet.setCategoryBudget(category, amount);  // Устанавливаем бюджет в кошелек пользователя
    }

    public void printSummary(User user) {
        Wallet wallet = user.getWallet();

        // Подсчет общих доходов, расходов и бюджета
        double totalIncome = wallet.getTotalIncome();
        double totalExpenses = wallet.getTotalExpenses();
        double totalBudget = wallet.getTotalBudget();
        double remainingBudget = totalBudget - totalExpenses;

        System.out.println("Общий доход: " + totalIncome);
        System.out.println("Общие расходы: " + totalExpenses);
        System.out.println("Общий бюджет: " + totalBudget);
        System.out.println("Общий остаток от бюджета: " + remainingBudget);

        // Выводим по категориям
        for (String category : wallet.getCategoryBudgets().keySet()) {
            double categoryIncome = wallet.getCategoryIncome(category);
            double categoryExpense = wallet.getCategoryExpense(category);
            double categoryBudget = wallet.getCategoryBudget(category);

            double remainingCategoryBudget = categoryBudget - categoryExpense;

            System.out.println(category + ": ");
            System.out.println("  Доход: " + categoryIncome);
            System.out.println("  Расход: " + categoryExpense);
            System.out.println("  Остаток бюджета: " + remainingCategoryBudget);

            // Проверка, если расходы превышают бюджет
            if (categoryExpense > categoryBudget) {
                System.out.println("  Внимание! Расходы превышают бюджет по категории!");
            }
        }

        // Проверка, если общие расходы превышают общие доходы
        if (totalExpenses > totalIncome) {
            System.out.println("  Внимание! Расходы превышают доходы!");
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("userData.ser"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.ser"))) {
            users.putAll((Map<String, User>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
