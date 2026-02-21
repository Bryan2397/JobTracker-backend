package com.job.tracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.job.tracker.dto.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {
    @Value("${OPENAI_URL}")
    private String apiUrl;

    @Value("${CHATGPT_SECRETKEY}")
    private String apiKey;

    private final WebClient webClient;

    @Autowired
    public ChatGPTService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    public JobResponse getChatResponse(String description) throws JsonProcessingException {
        String sanitized = santizeForJson(description);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4.1-mini");
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", sanitized)
        ));

        String response = webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        String aiResponse = root
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        JobResponse job = mapper.readValue(aiResponse, JobResponse.class);
        return job;
    }

    public String santizeForJson(String userText){
        if(userText == null) return "";
        return userText
                .replace("\\", "\\\\")
                        .replace("\"","\\\"")
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .replace("\n", "\\n");
    }
}
