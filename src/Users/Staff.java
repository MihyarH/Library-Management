package Users;

import Exceptions.DuplicateBookNameException;
import Modules.Book;

import java.util.Scanner;

public class Staff {

    public static void main(String[] args) throws DuplicateBookNameException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Create Book");
            System.out.println("2. Update Book");
            System.out.println("3. List Books");
            System.out.println("4. Delete Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Book.createBook();
                    break;
                case 2:
                    Book.updateBook();
                    break;
                case 3:
                    Book.listBooks();
                    break;
                case 4:
                    Book.deleteBook();
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

}
