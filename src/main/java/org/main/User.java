package org.main;

import java.io.Serializable;

public class User implements Serializable {
    private static int nextId = 1;

    private final int userId;
    private String name;
    private String email;
    private String phone;

    public User(String name, String email, String phone) {
        this.userId = nextId++;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return "User{id=" + userId + ", name='" + name + "', email='" + email + "', phone='" + phone + "'}";
    }
}
