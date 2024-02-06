package org.example.UsersDB;

import org.example.Users.User;
import org.example.Users.UserRole;
import java.sql.*;
import java.util.List;

public class UsersDataManipulator {

    public static void insert(String userName, String password, UserRole userRole) {

        String sql = "INSERT INTO Users (userName, password , userRole) VALUES (?, ?,?)";
        try (Connection conn = UsersDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, userRole.toString());
            pstmt.executeUpdate();
            System.out.println("Record inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(int id, String userName, String password) {
        String sql = "UPDATE users SET userName = ?, password = ? WHERE id = ?";

        try (Connection conn = UsersDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("Record updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String selectAll(){
        String sql = "SELECT * FROM users";
        StringBuilder allUsers = new StringBuilder();

        try (Connection conn = UsersDatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {

                allUsers.append("User ID :").append(rs.getInt("id")).append("\n");
                allUsers.append("User Name :").append(rs.getString("userName")).append("\n");
                allUsers.append("Password :").append(rs.getString("password")).append("\n");
                allUsers.append("User Role :").append(rs.getString("userRole")).append("\n");
                allUsers.append("\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allUsers.toString();
    }

    public static void delete(int id){

        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = UsersDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static boolean search(String userName){
        String sql = "SELECT * FROM users WHERE userName = ?";

        try (Connection conn = UsersDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<User> selectAllUserType (){
        String sql = "SELECT id, userName, password FROM users";

        try (Connection conn = UsersDatabaseConnector.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("userName") + "\t" +
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    public static Object getUserByUsername(String userName) {
        String sql = "SELECT * FROM users WHERE userName = ?";

        try (Connection conn = UsersDatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
    }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void CheckIfUsernameExists(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        Connection conn = UsersDatabaseConnector.connect();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Username already exists.");
            } else {
                System.out.println("Username does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


