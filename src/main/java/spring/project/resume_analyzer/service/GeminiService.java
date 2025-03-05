package spring.project.resume_analyzer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {
    private final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    @Value("${GEMINI_API_KEY}")
    private String API_KEY;

    private final List<Map<String, Object>> conversationHistory = new ArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();
}
