package com.example.destinationsuggester.controllers;

import com.example.destinationsuggester.DbConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class SignupController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestParam String username,
            @RequestParam String user_password,
            @RequestParam String confirm_password) {

        // CHECK IF PASSWORDS MATCH
        if (!user_password.equals(confirm_password)) {
            return ResponseEntity
                    .status(302)
                    .header("Location", "/signup.html?error=password_mismatch")
                    .build();
        }

        String checkSql = "SELECT * FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users (username, user_password) VALUES (?, ?)";

        try (Connection conn = DbConn.getConnection()) {

            // CHECK IF USERNAME EXISTS
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, username.trim());

            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                return ResponseEntity
                        .status(302)
                        .header("Location", "/signup.html?error=username_taken")
                        .build();
            }

            // INSERT USER
            PreparedStatement stmt = conn.prepareStatement(insertSql);

            String hashedPassword = passwordEncoder.encode(user_password.trim());

            stmt.setString(1, username.trim());
            stmt.setString(2, hashedPassword);

            stmt.executeUpdate();

            return ResponseEntity
                    .status(302)
                    .header("Location", "/login.html")
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(302)
                    .header("Location", "/signup.html?error=db")
                    .build();
        }
    }
}