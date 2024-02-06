package Users;

import Exceptions.DuplicateBookNameException;
import Exceptions.DuplicateUsernameException;

import java.util.Scanner;

public class LoginManager {
    static boolean loginStatus;

    public static void login() throws DuplicateUsernameException, DuplicateBookNameException {
        while (true) {
            String inputUsername = UserInputValidator.validateUsername();
            String inputPassword = UserInputValidator.validatePassword();

            User loggedInUser = User.getUserByUsername(inputUsername);

            if (loggedInUser != null && loggedInUser.checkLogin(inputUsername, inputPassword)) {
                System.out.println("Login successful!");
                System.out.println("Welcome Back " + inputUsername);

                if (loggedInUser.isAdmin()) {
                    System.out.println("You are an admin. Perform admin actions here.");
                    Admin.main(null);
                } else if (loggedInUser.isStaff()) {
                    System.out.println("You are a staff member. Perform staff actions here.");
                    Staff.main(null);
                }

                loginStatus = true;
                break;
            } else {
                System.out.println("Login failed. Please check your username and password.");
                loginStatus = false;
            }
        }
    }
}
