package org.main;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static final List<User> users = new ArrayList<>();
    private static final List<Account> account = new ArrayList<>();
    
    public static void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user);
    }

    public static void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added: " + account.getAccountNumber());
    }

    public static List<User> getUsers() {
        return users;
    }
}
