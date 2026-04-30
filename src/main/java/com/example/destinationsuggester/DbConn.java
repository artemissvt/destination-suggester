package com.example.destinationsuggester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/destinationdb";
            String user = "root";
            String password = "Password1";

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException se) {
            throw new RuntimeException("Database connection failed: " + se.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
    }
}