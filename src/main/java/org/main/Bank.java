package org.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.main.exceptions.InvalidSaveDataException;

public class Bank implements Serializable {
    private final String saveDataFilename;

    public Bank(String saveDataFilename) {
        this.saveDataFilename = saveDataFilename;
    }

    private final List<User> users = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user);
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added: " + account.getAccountNumber());
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Account> getAccounts() {
    	return accounts;
    }

    public Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;

    }
    
    public User findUserById(int userId) {
    	for (User user : users) {
    		if (user.getUserId() == userId) {
    			return user;
    		}
    	}
    	
    	return null; 
    	
    }

    public List<Account> searchByCustomerName(String name) {
        List<Account> results = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getOwner().getName().equalsIgnoreCase(name)) {
                results.add(acc);
            }
        }
        return results;
    }

    public List<Account> getAccountsByName(String name) {
    List<Account> results = new ArrayList<>();
    for (Account acc : accounts) {
        if (acc.getOwner().getName().equalsIgnoreCase(name)) {
            results.add(acc);
        }
    }
    return results;
}



    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream(saveDataFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);

            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error while saving bank data: " + e.getMessage());
        }
    }

    public static Bank load(String filename) throws InvalidSaveDataException {
        File file = new File(filename);
        if (!file.exists() || file.isDirectory()) {
            return new Bank(filename);
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            return (Bank) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new InvalidSaveDataException(e.getMessage());
        }
    }
    

}
