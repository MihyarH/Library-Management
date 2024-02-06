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
    private int userId;
    private UserRole role;

    public User(int userId,String username, String password, UserRole role) {
        this.userId = userId;
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
    public int getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public boolean isStaff() {
        return role == UserRole.USER;
    }

    public static User getUserByUsername(String username) {
        List<User> users = loadUsersFromFile("users.txt");
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void saveUsersToFile(List<User> users, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.getUserId() + ","+ user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "\n");
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

    public static List<User> loadUsersFromFile(String filePath) {
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
                int userId = Integer.parseInt(userParts[0]);
                String username = userParts[1];
                String password = userParts[2];
                UserRole role = UserRole.valueOf(userParts[3]);
                User user = new User(userId,username, password, role);
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

    public static void createUser(int userId,String username, String password, UserRole role) throws DuplicateUsernameException {
        if (username == null || password == null || role == null) {
            System.err.println("Invalid input parameters");
            return;
        }

        if (!checkIfUsernameExists(username)) {
            User newUser = new User(userId,username, password, role);
            List<User> userList = loadUsersFromFile("users.txt");
            userList.add(newUser);
            saveUsersToFile(userList, "users.txt");
            System.out.println("User created successfully.");
        } else {
            throw new DuplicateUsernameException("Error: Username already exists.");
        }
    }

    public static void updateUser(String username, String newPassword) {
        List<User> users = loadUsersFromFile("users.txt");
        boolean userFound = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                user.password = (newPassword);
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
    }

    public static void listUsers() {
        List<User> users = loadUsersFromFile("users.txt");

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("List of Users:");
            for (User user : users) {
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Role: " + user.getRole());
                System.out.println("----------------------");
            }
        }
    }

    public static void deleteUser(int userId) {
        List<User> users = loadUsersFromFile("users.txt");
        boolean userFound = false;

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUserId() == userId) {
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
    }
}
