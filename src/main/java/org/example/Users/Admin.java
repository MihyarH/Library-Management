package org.example.Users;

import org.example.Exceptions.DuplicateUsernameException;

import java.util.Scanner;

public class Admin {
    public static void main(String[] args) throws DuplicateUsernameException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. List Users");
            System.out.println("4. Delete User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
//                case 1:
//                    createUser(scanner);
//                    break;
                case 2:
                    updateUser(scanner);
                    break;
                case 3:
                    User.listUsers();
                    break;
                case 4:
                    deleteUser(scanner);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

//    private static void createUser(Scanner scanner) throws DuplicateUsernameException {
//        System.out.print("Enter user ID: ");
//        int userId = scanner.nextInt();
//
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//
//        System.out.print("Enter role (ADMIN or USER): ");
//        String roleString = scanner.nextLine();
//        UserRole role = UserRole.valueOf(roleString.toUpperCase());
//
//        User.createUser(userId,username, password, role);
//        System.out.println("User created successfully.");
//    }

    private static void updateUser(Scanner scanner) {
        // Get user input for username and new password
        System.out.print("Enter username to update: ");
        String username = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        // Call the updateUser method
//        User.updateUser(username, newPassword);
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("Enter username to delete: ");
        int userId = scanner.nextInt();

        User.deleteUser(userId);
    }
}
