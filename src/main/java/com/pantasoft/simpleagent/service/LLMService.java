package com.pantasoft.simpleagent.service;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pantasoft.simpleagent.config.LLMConfig;

@Service
public class LLMService {

    private final CloseableHttpClient httpClient;
    private final LLMConfig llmConfig;
    private final ObjectMapper objectMapper;

    @Autowired
    public LLMService(CloseableHttpClient httpClient, LLMConfig llmConfig) {
        this.httpClient = httpClient;
        this.llmConfig = llmConfig;
        this.objectMapper = new ObjectMapper();
    }

    public String getCompletion(String prompt) {
        try {
            // For testing purposes, return a mock response
            return getMockResponse(prompt);

            /*
             * Uncomment this when you have a valid API key with sufficient balance
             * HttpPost request = new HttpPost(llmConfig.getDeepseekApiUrl());
             * request.setHeader("Authorization", "Bearer " +
             * llmConfig.getDeepseekApiKey());
             * request.setHeader("Content-Type", "application/json");
             * 
             * ObjectNode requestBody = objectMapper.createObjectNode();
             * requestBody.put("model", "deepseek-chat");
             * requestBody.put("messages", objectMapper.createArrayNode()
             * .add(objectMapper.createObjectNode()
             * .put("role", "user")
             * .put("content", prompt)));
             * requestBody.put("max_tokens", 1000);
             * requestBody.put("temperature", 0.7);
             * 
             * StringEntity entity = new StringEntity(requestBody.toString(),
             * ContentType.APPLICATION_JSON);
             * request.setEntity(entity);
             * 
             * return httpClient.execute(request, response -> {
             * String responseBody = new
             * String(response.getEntity().getContent().readAllBytes(),
             * StandardCharsets.UTF_8);
             * ObjectNode responseJson = (ObjectNode) objectMapper.readTree(responseBody);
             * 
             * if (responseJson.has("error")) {
             * throw new RuntimeException("API Error: " +
             * responseJson.get("error").get("message").asText());
             * }
             * 
             * if (responseJson.has("choices") && responseJson.get("choices").isArray() &&
             * responseJson.get("choices").size() > 0) {
             * ObjectNode choice = (ObjectNode) responseJson.get("choices").get(0);
             * if (choice.has("text")) {
             * return choice.get("text").asText();
             * } else if (choice.has("message") && choice.get("message").has("content")) {
             * return choice.get("message").get("content").asText();
             * }
             * }
             * 
             * return responseBody;
             * });
             */
        } catch (Exception e) {
            throw new RuntimeException("Error processing request: " + e.getMessage(), e);
        }
    }

    private String getMockResponse(String prompt) {
        // Simple mock responses for testing
        if (prompt.toLowerCase().contains("capital of france")) {
            return "The capital of France is Paris. It is one of the most populous cities in Europe and serves as the country's major economic and cultural center.";
        } else if (prompt.toLowerCase().contains("hello") || prompt.toLowerCase().contains("hi")) {
            return "Hello! I'm your AI assistant. How can I help you today?";
        } else if (prompt.toLowerCase().contains("weather")) {
            return "I'm sorry, I don't have access to real-time weather data. You might want to check a weather service for that information.";
        } else {
            return "I understand your question about: " + prompt
                    + ". This is a mock response for testing purposes. In a production environment, this would be a real AI-generated response.";
        }
    }
}