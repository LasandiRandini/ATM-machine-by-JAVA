package com.atm;

import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    private HashMap<String, User> users;
    private HashMap<String, Account> accounts;
    private Scanner scanner;

    public ATM() {
        users = new HashMap<>();
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);

        // Add a sample user and account for testing
        User user = new User("testuser", "password");
        Account account = new Account(1000.0);
        users.put(user.getUsername(), user);
        accounts.put(user.getUsername(), account);
    }

    public void start() {
        System.out.println("Welcome to the ATM");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (authenticateUser(username, password)) {
            System.out.println("Authentication successful.");
            showMenu(username);
        } else {
            System.out.println("Authentication failed.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.checkPassword(password);
    }

    private void showMenu(String username) {
        Account account = accounts.get(username);
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
