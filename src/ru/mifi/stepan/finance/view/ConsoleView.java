package ru.mifi.stepan.finance.view;

import ru.mifi.stepan.finance.controller.UserController;
import ru.mifi.stepan.finance.model.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final UserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleView(UserController userController) {
        this.userController = userController;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            System.out.println("3. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    showRegistrationMenu();
                    break;
                case 2:
                    showLoginMenu();
                    break;
                case 3:
                    System.out.println("Выход...");
                    userController.saveData();
                    return;
                default:
                    System.out.println("Некорректный выбор.");
            }
        }
    }

    public void showRegistrationMenu() {
        System.out.println("Регистрация:");

        System.out.print("Введите логин: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (userController.registerUser(username, password)) {
            System.out.println("Регистрация прошла успешно.");
            showLoginMenu();  // Автоматическая авторизация
        } else {
            System.out.println("Логин уже существует.");
        }
    }

    public void showLoginMenu() {
        System.out.println("Авторизация:");

        System.out.print("Введите логин: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        User user = userController.loginUser(username, password);
        if (user != null) {
            showBudgetMenu(user);
        } else {
            System.out.println("Неверный логин или пароль.");
        }
    }

    public void showBudgetMenu(User user) {
        if (user == null) {
            System.out.println("Необходимо войти в систему.");
            return;
        }

        while (true) {
            System.out.println("Меню бюджета:");
            System.out.println("1. Установить бюджет");
            System.out.println("2. Добавить доход");
            System.out.println("3. Добавить расход");
            System.out.println("4. Показать финансовое состояние");
            System.out.println("5. Назад");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    setCategoryBudget(user);
                    break;
                case 2:
                    addIncome(user);
                    break;
                case 3:
                    addExpense(user);
                    break;
                case 4:
                    userController.printSummary(user);
                    break;
                case 5:
                    return; // Возврат в главное меню
                default:
                    System.out.println("Некорректный выбор.");
            }
        }
    }

    public void setCategoryBudget(User user) {
        System.out.print("Введите категорию: ");
        String category = scanner.nextLine();

        Double amount = getValidAmount("Введите сумму бюджета: ");
        if (amount != null) {
            userController.setCategoryBudget(user, category, amount);
        }
    }

    public void addIncome(User user) {
        System.out.print("Введите категорию: ");
        String category = scanner.nextLine();

        Double amount = getValidAmount("Введите сумму дохода: ");
        if (amount != null) {
            userController.addIncome(user, category, amount);
        }
    }

    public void addExpense(User user) {
        System.out.print("Введите категорию: ");
        String category = scanner.nextLine();

        Double amount = getValidAmount("Введите сумму расхода: ");
        if (amount != null) {
            userController.addExpense(user, category, amount);
        }
    }

    private Double getValidAmount(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                Double amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("Сумма не может быть отрицательной. Попробуйте снова.");
                } else {
                    return amount;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число");
                scanner.nextLine(); // Очистка буфера после неправильного ввода
            }
        }
    }
}
