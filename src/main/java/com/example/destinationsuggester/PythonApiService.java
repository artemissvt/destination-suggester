package com.example.destinationsuggester;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;
@Service
public class PythonApiService {

    private final RestTemplate restTemplate;

    public PythonApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callPython(String text, String session_id) {

        String url = "http://localhost:8000/recommend";

        Map<String, String> body = new HashMap<>();
        body.put("text", text);
        body.put("session_id", session_id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}