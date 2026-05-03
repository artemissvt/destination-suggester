package com.example.destinationsuggester.controllers;

import com.example.destinationsuggester.DbConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

        //  CHECK IF PASSWORDS MATCH
        if (!user_password.equals(confirm_password)) {
            return ResponseEntity
                    .badRequest()
                    .body("Passwords do not match!");
        }

        String sql = "INSERT INTO users (username, user_password) VALUES (?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String hashedPassword = passwordEncoder.encode(user_password);

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            stmt.executeUpdate();
                    return ResponseEntity
                    .status(302)
                    .header("Location", "/login.html")
                    .build();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating user.");
        }
    }
}