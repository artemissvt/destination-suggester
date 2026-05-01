package com.example.destinationsuggester;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AiController {

    private final PythonApiService pythonApiService;

    public AiController(PythonApiService pythonApiService) {
        this.pythonApiService = pythonApiService;
    }

    @PostMapping("/generate")
    public String generate(
            @RequestParam String destinationDescription,
            HttpSession session) {

        String sessionId = session.getId();

        String resultJson = pythonApiService.getRecommendations(destinationDescription, sessionId);

        // store FULL JSON
        session.setAttribute("result", resultJson);

        return "redirect:/result.html";
    }

    @GetMapping("/get-result")
    @ResponseBody
    public String getResult(HttpSession session) {
        Object result = session.getAttribute("result");
        return result != null ? result.toString() : "{}";
    }
}