package com.medical.dentist.module.chatgpt.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIRestTemplateConfig {

  private final OpenAIProperties openAIProperties;

  public OpenAIRestTemplateConfig(
      OpenAIProperties openAIProperties) {
    this.openAIProperties = openAIProperties;
  }

  @Bean
  @Qualifier("openaiRestTemplate")
  public RestTemplate openaiRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.getInterceptors().add((request, body, execution) -> {
      request.getHeaders()
          .add("Authorization", "Bearer " + openAIProperties.getApi().getKey());
      return execution.execute(request, body);
    });
    return restTemplate;
  }
}
