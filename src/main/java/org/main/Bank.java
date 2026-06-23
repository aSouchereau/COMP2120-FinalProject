package org.main;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static final List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user);
    }

    public static List<User> getUsers() {
        return users;
    }
}
