package com.openaiSpring.openaiSpring.controller;

import com.openaiSpring.openaiSpring.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PromptController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/prompt")
    public String getOpenaiResponse(@RequestBody String prompt) {

        return chatGPTService.prompt(prompt);
    }
}
