package ru.mifi.stepan.finance.model;

import java.io.Serializable;

public class User implements Serializable {
    private final String username;
    private final String password;
    private final Wallet wallet;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new Wallet();  // Каждый пользователь имеет свой кошелек
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
