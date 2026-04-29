package com.example.destinationsuggester;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class SignupController {

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password) {

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // set values from form
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            // execute insert
            stmt.executeUpdate();

            return ResponseEntity.ok("Signup successful! You can now log in.");

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating user.");
        }
    }
}