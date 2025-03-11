package spring.project.resume_analyzer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.project.resume_analyzer.request.GeminiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {
    private final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    @Value("${gemini.api.key}")
    private String API_KEY;

    @Value("${gemini.initial.prompt}")
    private String geminiInitialPrompt;

    private final List<Map<String, Object>> conversationHistory = new ArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<?> geminiAnalyzeResume(String fileContent) {
        try {
            StringBuilder prompt = new StringBuilder(geminiInitialPrompt + "Analyze this resume: " + fileContent);

            conversationHistory.add(Map.of(
                    "role", "user",
                    "parts", List.of(Map.of("text", prompt))
            ));

            Map<String, Object> requestPayload = Map.of("contents", conversationHistory);
            String requestBody = objectMapper.writeValueAsString(requestPayload);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> aiResponse = restTemplate.exchange(
                    URL + API_KEY,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            GeminiResponse responseObject = objectMapper.readValue(aiResponse.getBody(), GeminiResponse.class);
            String responseText = responseObject
                    .getCandidates()
                    .getFirst()
                    .getContent()
                    .getParts()
                    .getFirst()
                    .getText()
                    .trim()
                    .replace("```json", "")
                    .replace("```", "");

            if (responseText == null || responseText.isBlank())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No response from AI!");

            conversationHistory.add(Map.of(
                    "role", "model",
                    "parts",List.of(Map.of("text", responseText))
            ));

            return ResponseEntity.ok(responseText);
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
