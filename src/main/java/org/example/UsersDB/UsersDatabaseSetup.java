package org.example.UsersDB;

import java.sql.Connection;
import java.sql.Statement;

public class UsersDatabaseSetup {

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " userName TEXT NOT NULL,\n"
                + " password TEXT NOT NULL,\n"
                + " userRole TEXT NOT NULL\n"
                + ");";

        try (Connection conn = UsersDatabaseConnector.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

