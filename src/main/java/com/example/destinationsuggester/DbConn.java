package com.example.destinationsuggester;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
    public static Connection getConnection() {

        // initialize connection with the database
        Connection conn = null;
        try {

            // connect the db url and the user with its password ( already made on the database)
            String url = "jdbc:mysql://localhost:3306/hangmangame";
            String user = "root";
            String password = "Password1";
            conn = DriverManager.getConnection(url, user, password);
            return conn;

        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
    }
}