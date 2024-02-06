package org.example.BooksDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BooksDatabaseConnector {

    public static Connection connect() {
        String url = "jdbc:sqlite:Database.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}

