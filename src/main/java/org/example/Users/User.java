package org.example.Users;

import org.example.Exceptions.DuplicateUsernameException;
import org.example.UsersDB.UsersDataManipulator;
import org.example.UsersDB.UsersDatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private static String username;
    private static String password;
    private int userId;
    private UserRole role;

    public User(int userId,String username, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static String getUsername() {
        return (username);
    }

    public static String getPassword() {
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
        return role == UserRole.STAFF;
    }

    public void setUserId(String userId) {
        this.userId = Integer.parseInt(userId);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) { this.password = password; }
    public void setRole(UserRole role) { this.role = role; }


    public void updateUserConstructor(int userId, String username, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static void createUser(String userName, String password) throws DuplicateUsernameException, SQLException {
        if (userName == null || password == null) {
            System.err.println("Invalid input parameters");
            return;
        }

        if (!checkIfUsernameExists(userName)) {
            UsersDataManipulator.insert(userName, password,UserRole.STAFF);
            System.out.println("User created successfully.");
        } else {
            throw new DuplicateUsernameException("Error: Username already exists.");
        }
    }

    private static boolean checkIfUsernameExists(String userName) {
        if (UsersDataManipulator.search(userName)) {
            System.out.println("Username already exists.");
            return true;
        }
        return false;
    }

    public static void updateUser(int id ,String userName, String password) {
        UsersDataManipulator.update(id,userName,password);
    }

    public static void listUsers() {
        UsersDataManipulator.selectAll();
    }

    public static void deleteUser(int userId) {
        UsersDataManipulator.delete(userId);
    }

    public static User getUserByUsername(String userName) {
        String sql = "SELECT * FROM users WHERE userName = ?";

        try (Connection conn = UsersDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String password = rs.getString("password");
                UserRole role = UserRole.valueOf(rs.getString("userRole"));

                return new User(userId, userName, password, role);
            } else { // The else block should be here
                System.out.println("User not found in the database.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




    public static boolean checkLogin(String inputUsername, String inputPassword) {
        User user = getUserByUsername(inputUsername);

        if (user != null) {
            return user.getPassword().equals(inputPassword);
        }
        return false;
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

}



