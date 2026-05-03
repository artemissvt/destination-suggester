package com.example.destinationsuggester.controllers;

import com.example.destinationsuggester.DbConn;
import com.example.destinationsuggester.services.PythonApiService;
import com.example.destinationsuggester.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AiController {

    @Autowired
    private PythonApiService pythonApiService;

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/generate")
    public String generate(
            @RequestParam String destinationDescription,
            HttpSession session) {

        // get session + user info
        String sessionId = session.getId();
        Integer user_id = (Integer) session.getAttribute("user_id");

        // call FastAPI
        String resultJson = pythonApiService
                .getRecommendations(destinationDescription, sessionId);

        // store result for result.html
        session.setAttribute("result", resultJson);

        // save to DB (only if logged in properly)
        if (user_id != null) {
            recommendationService.saveRecommendation(user_id, resultJson);
        }

        return "redirect:/result.html";
    }

    /**
     * Endpoint for result.html to fetch stored recommendations
     */
    @GetMapping("/get-result")
    @ResponseBody
    public String getResult(HttpSession session) {

        Object result = session.getAttribute("result");

        if (result == null) {
            return "{}";
        }

        return result.toString(); // already JSON
    }

    @GetMapping("/history")
    @ResponseBody
    public List<String> getHistory(HttpSession session) {

        Integer user_id = (Integer) session.getAttribute("user_id");

        List<String> results = new ArrayList<>();

        if (user_id == null) return results;

        String sql = "SELECT recommendation_data FROM recommendations WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(rs.getString("recommendation_data"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}