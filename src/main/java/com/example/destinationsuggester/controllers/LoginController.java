package com.example.destinationsuggester.controllers;

import com.example.destinationsuggester.DbConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String user_password,
            HttpSession session) {

        String sql = "SELECT user_id, user_password FROM users WHERE username = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username.trim());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int user_id = rs.getInt("user_id"); // ✅ comes from DB
                String storedHash = rs.getString("user_password");

                if (passwordEncoder.matches(user_password.trim(), storedHash)) {

                    session.setAttribute("user", username);
                    session.setAttribute("user_id", user_id); // ✅ store in session

                    return "redirect:/home.html";
                }
            }

            return "redirect:/login.html?error=true";

        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:/login.html?error=db";
        }
    }
}