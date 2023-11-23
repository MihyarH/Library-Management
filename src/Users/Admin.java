package Users;

import Exceptions.DuplicateUsernameException;

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
            scanner.nextLine();

            switch (choice) {
                case 1:
                    User.createUser(UserRole.USER);
                    break;
                case 2:
                    User.updateUser();
                    break;
                case 3:
                    User.listUsers();
                    break;
                case 4:
                    User.deleteUser();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }}
