package com.example.destinationsuggester.services;

import com.example.destinationsuggester.DbConn;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class RecommendationService {

    public void saveRecommendation(Integer user_id, String jsonData) {

        String sql = "INSERT INTO recommendations (user_id, recommendation_data) VALUES (?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user_id);
            stmt.setString(2, jsonData);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}