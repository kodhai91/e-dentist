package com.medical.dentist.module.chatgpt.controller;

import com.medical.dentist.module.chatgpt.config.OpenAIProperties;
import com.medical.dentist.module.chatgpt.model.ChatRequest;
import com.medical.dentist.module.chatgpt.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChatController {

  @Qualifier("openaiRestTemplate")
  private final RestTemplate restTemplate;

  private final OpenAIProperties openAIProperties;

  public ChatController(RestTemplate restTemplate,
      OpenAIProperties openAIProperties) {
    this.restTemplate = restTemplate;
    this.openAIProperties = openAIProperties;
  }


  @GetMapping("/chat")
  public String chat(@RequestParam String prompt) {
    // create a request
    ChatRequest request = new ChatRequest(openAIProperties.getModel(), prompt);

    // call the API
    ChatResponse response = restTemplate.postForObject(openAIProperties.getApi().getUrl(), request,
        ChatResponse.class);

    if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
      return "No response";
    }

    // return the first response
    return response.getChoices().get(0).getMessage().getContent();
  }

}
