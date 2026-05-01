package com.example.destinationsuggester;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/check-session")
    public Object checkSession(HttpSession session) {

        Object user = session.getAttribute("user");

        if (user != null) {
            return new Object() {
                public final String status = "LOGGED_IN";
                public final String username = user.toString();
                public final String session_id = session.getId();
            };
        } else {
            return new Object() {
                public final String status = "NOT_LOGGED_IN";
            };
        }
    }
}