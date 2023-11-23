package Users;

import java.util.Scanner;

public class UserInputValidator {

    public static String validateUsername() {
        Scanner scanner = new Scanner(System.in);
        String username;

        do {
            System.out.println("Please enter your username: ");
            username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Username cannot be empty. Please enter a valid username.");
            } else if (!isValidUsername(username)) {
                System.out.println("Invalid username. Please use only letters, numbers, and underscores.");
            }

        } while (username.isEmpty() || !isValidUsername(username));

        return username;
    }

    public static String validatePassword() {
        Scanner scanner = new Scanner(System.in);
        String password;

        do {
            System.out.println("Please enter your password: ");
            password = scanner.nextLine();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please enter a valid password.");
            }

        } while (password.isEmpty());

        return password;
    }

    private static boolean isValidUsername(String username) {
        return username.matches("^[a-zA-Z0-9_]+$");
    }
}
