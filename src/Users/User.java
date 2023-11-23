package Users;

import Exceptions.DuplicateUsernameException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class User {
        private String username;
        private String password;
        private UserRole role;

        public User(String username, String password, UserRole role) {
            this.username = username;
            this.password = password;
            this.role = role;

        }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public boolean isStaff() {
        return role == UserRole.USER;
    }

    public static User getUserByUsername(String username) {
        String filePath = "users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String user = userData[0];
                    String password = userData[1];
                    String role = userData[2];
                    if (user.equals(username)) {
                        return new User(user, password, UserRole.valueOf(role));
                    }
                } else {
                    System.err.println("Invalid user data: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read from file " + filePath + ": " + e.getMessage());
        }
        return null;
    }

        public static void saveUsersToFile(List<User> users, String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (User user : users) {
                    writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "\n");
                }
                System.out.println("Users saved to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to save users to file.");
            }
        }

        public static boolean checkLogin(String username, String password) {
        List<User> users = loadUsersFromFile("users.txt");
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

        private static List<User> loadUsersFromFile(String filePath) {
            List<User> users = new ArrayList<>();
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("File " + filePath + " created successfully.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Failed to create file " + filePath);
                    return users;
                }
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userParts = line.split(",");
                    String username = userParts[0];
                    String password = userParts[1];
                    UserRole role = UserRole.valueOf(userParts[2]);
                    User user = new User(username, password, role);
                    users.add(user);
                }
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
                System.err.println("Failed to read from file " + filePath);
            }
            return users;
        }

        private static boolean checkIfUsernameExists(String username) {
            List<User> users = loadUsersFromFile("users.txt");
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }

    public static void createUser(UserRole role) throws DuplicateUsernameException {
        Scanner scanner = new Scanner(System.in);
        String username = validateUsername(scanner);
        String password = validatePassword(scanner);

        if (username == null || password == null || role == null) {
            System.err.println("Invalid input parameters");
            return;
        }

        if (!checkIfUsernameExists(username)) {
            User newUser = new User(username, password, role);
            List<User> userList = loadUsersFromFile("users.txt");
            userList.add(newUser);
            saveUsersToFile(userList, "users.txt");
            System.out.println("User created successfully.");
        } else {
            throw new DuplicateUsernameException("Error: Username already exists.");
        }
        scanner.close();
    }

    private static String validateUsername(Scanner scanner) {
        String username;
        do {
            System.out.print("Enter your new username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                System.out.println("Username cannot be empty. Please enter a valid username.");
            }
        } while (username.isEmpty());
        return username;
    }

    private static String validatePassword(Scanner scanner) {
        String password;
        do {
            System.out.print("Enter your new password: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please enter a valid password.");
            }
        } while (password.isEmpty());
        return password;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public static void updateUser() {
        Scanner scanner = new Scanner(System.in);
        String username = validateUsername(scanner);
        String newPassword = validateNewPassword(scanner);
        List<User> users = loadUsersFromFile("users.txt");
        boolean userFound = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.setPassword(newPassword);
                userFound = true;
                break;
            }
        }
        if (userFound) {
            saveUsersToFile(users, "users.txt");
            System.out.println("User password updated successfully.");
        } else {
            System.out.println("User not found. Password not updated.");
        }
        scanner.close();
    }
    private static String validateNewPassword(Scanner scanner) {
        String password;
        do {
            System.out.print("Enter your new password: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please enter a valid password.");
            }
        } while (password.isEmpty());
        return password;
    }

    public static void listUsers() {
        List<User> users = loadUsersFromFile("users.txt");

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of Users:");
            for (User user : users) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Role: " + user.getRole());
                System.out.println("----------------------");
            }
        }
    }

    public static void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        String username = validateUsername(scanner);

        List<User> users = loadUsersFromFile("users.txt");
        boolean userFound = false;

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(username)) {
                iterator.remove();
                userFound = true;
                break;
            }
        }
        if (userFound) {
            saveUsersToFile(users, "users.txt");
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found. Deletion failed.");
        }
        scanner.close();
    }
}