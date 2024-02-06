package org.example.BooksDB;

import org.example.UsersDB.UsersDatabaseConnector;
import java.sql.Connection;
import java.sql.Statement;

public class BooksDatabaseSetup {

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Books (\n" +
                " id integer PRIMARY KEY,\n" +
                " bookName text NOT NULL,\n" +
                " author text NOT NULL,\n" +
                " quantity integer NOT NULL,\n" +
                " price integer NOT NULL,\n" +
                " language text NOT NULL,\n" +
                " year integer NOT NULL\n" +
                ");";


        try (Connection conn = UsersDatabaseConnector.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

