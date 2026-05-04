package com.example.destinationsuggester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    public static Connection getConnection() {
        try {
            String host = System.getenv().getOrDefault("DB_HOST", "localhost");
            String port = System.getenv().getOrDefault("DB_PORT", "3306");
            String name = System.getenv().getOrDefault("DB_NAME", "destinationdb");
            String user = System.getenv().getOrDefault("DB_USER", "root");
            String password = System.getenv().getOrDefault("DB_PASSWORD", "Password1");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + name
                    + "?sslMode=REQUIRED&serverTimezone=UTC";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException se) {
            throw new RuntimeException("Database connection failed: " + se.getMessage());
        }
    }
}