package com.example.destinationsuggester;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/check-session")
    public String checkSession(HttpSession session) {

        if (session.getAttribute("user") != null) {
            return "LOGGED_IN";
        } else {
            return "NOT_LOGGED_IN";
        }
    }
}