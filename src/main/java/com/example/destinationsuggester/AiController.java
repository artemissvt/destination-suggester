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
            @RequestParam("destinationDescription") String text,
            HttpSession session,
            Model model) {

        String session_id = session.getId();

        String response = pythonApiService.callPython(text, session_id);

        model.addAttribute("result", response);

        return "result";
    }
}