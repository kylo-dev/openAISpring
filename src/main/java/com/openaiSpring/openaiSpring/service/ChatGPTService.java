package com.openaiSpring.openaiSpring.service;

import com.openaiSpring.openaiSpring.config.ChatGPTConfig;
import com.openaiSpring.openaiSpring.dto.ChatRequest;
import com.openaiSpring.openaiSpring.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.url}")
    private String url;

    public String prompt(String prompt) {

        // 토큰 정보가 포함된 Header 가져오기
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // Create request
        ChatRequest chatRequest = new ChatRequest(model, prompt);

        // 통신을 위한 RestTemplate 구성하기
        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(chatRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ChatResponse response = restTemplate.postForObject(url, requestEntity, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException();
        }

        return response.getChoices().get(0).getMessage().getContent();
    }
}
