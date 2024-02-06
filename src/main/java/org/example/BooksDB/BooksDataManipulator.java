package org.example.BooksDB;

import java.sql.*;

public class BooksDataManipulator {

    public static void insert (String bookName, String author, int quantity, int price, String language, int year) throws SQLException {

        String sql = "INSERT INTO Books (bookName, author, quantity, price, language, year) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookName);
            pstmt.setString(2, author);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, price);
            pstmt.setString(5, language);
            pstmt.setInt(6, year);
            ((PreparedStatement) pstmt).executeUpdate();
            System.out.println("Record inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void update(int id, String bookName, String author, int quantity, int price, String language, int year) {
        String sql = "UPDATE Books SET bookName = ?, author = ?, quantity = ?, price = ?, language = ?, year = ? WHERE id = ?";

        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookName);
            pstmt.setString(2, author);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, price);
            pstmt.setString(5, language);
            pstmt.setInt(6, year);
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
            System.out.println("Record updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id){
        String sql = "DELETE FROM Books WHERE id = ?";

        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void search(String bookName){
        String sql = "SELECT id, bookName, author, quantity, price, language, year FROM Books WHERE bookName LIKE ?";

        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + bookName + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("bookName") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getInt("quantity") + "\t" +
                        rs.getInt("price") + "\t" +
                        rs.getString("language") + "\t" +
                        rs.getInt("year"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAll(){
        String sql = "SELECT * FROM Books";

        try (Connection conn = BooksDatabaseConnector.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("bookName") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getInt("quantity") + "\t" +
                        rs.getInt("price") + "\t" +
                        rs.getString("language") + "\t" +
                        rs.getInt("year"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkIfBookExists(String bookName) {
        String sql = "SELECT id FROM Books WHERE bookName = ?";
        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookName);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean checkIfBooksExistsUsingId(int bookId){
        String sql = "SELECT id FROM Books WHERE id = ?";
        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static String printBookInfo(String bookName){
        String sql = "SELECT * FROM Books WHERE bookName = ?";
        StringBuilder bookInfo = new StringBuilder();

        try (Connection conn = BooksDatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                bookInfo.append("Book ID: ").append(rs.getInt("id")).append("\n");
                bookInfo.append("Book Name: ").append(rs.getString("bookName")).append("\n");
                bookInfo.append("Author: ").append(rs.getString("author")).append("\n");
                bookInfo.append("Quantity: ").append(rs.getInt("quantity")).append("\n");
                bookInfo.append("Price: ").append(rs.getInt("price")).append("\n");
                bookInfo.append("Language: ").append(rs.getString("language")).append("\n");
                bookInfo.append("Publish Year: ").append(rs.getString("year")).append("\n");
            } else {
                bookInfo.append("No information found for book: ").append(bookName);
            }
        } catch (SQLException e) {
            return "An error occurred: " + e.getMessage();
        }

        return bookInfo.toString();
    }

    public static String getAllBooks() {
        String sql = "SELECT * FROM Books";
        StringBuilder allBooks = new StringBuilder();

        try (Connection conn = BooksDatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                allBooks.append("Book ID: ").append(rs.getInt("id")).append("\n");
                allBooks.append("Book Name: ").append(rs.getString("bookName")).append("\n");
                allBooks.append("Author: ").append(rs.getString("author")).append("\n");
                allBooks.append("Quantity: ").append(rs.getInt("quantity")).append("\n");
                allBooks.append("Price: ").append(rs.getInt("price")).append("\n");
                allBooks.append("Language: ").append(rs.getString("language")).append("\n");
                allBooks.append("Publish Year: ").append(rs.getString("year")).append("\n");
                allBooks.append("\n");
            }
        } catch (SQLException e) {
            return "An error occurred: " + e.getMessage();
        }

        return allBooks.toString();
    }

    public static void getBookIdByName(String name) {
        String sql = "SELECT id FROM Books WHERE bookName = ?";
        try {
            Connection conn = BooksDatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

