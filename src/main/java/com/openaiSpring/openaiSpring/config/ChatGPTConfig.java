package com.openaiSpring.openaiSpring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ChatGPTConfig {

    @Value("${openai.key}")
    private String openaiSecretKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiSecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            request.getHeaders().add("Authorization",
//                    "Bearer " + openaiSecretKey);
//            request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//            return execution.execute(request, body);
//        });
//        return restTemplate;
//    }
}
