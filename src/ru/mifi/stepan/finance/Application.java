package ru.mifi.stepan.finance;

import ru.mifi.stepan.finance.controller.UserController;
import ru.mifi.stepan.finance.model.FinanceManager;
import ru.mifi.stepan.finance.view.ConsoleView;

public class Application {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();
        UserController userController = new UserController(financeManager);
        ConsoleView consoleView = new ConsoleView(userController);

        consoleView.showMainMenu();
    }
}
