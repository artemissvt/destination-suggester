package com.example.destinationsuggester.services;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
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

    public String getRecommendations(String text, String sessionId) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(10000);

        RestTemplate restTemplate = new RestTemplate(factory);

        String url = "https://ml-travel-suggest-2568f.ondigitalocean.app/recommend";

        Map<String, String> body = new HashMap<>();
        body.put("text", text);
        body.put("session_id", sessionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        return response.getBody();
    }
}