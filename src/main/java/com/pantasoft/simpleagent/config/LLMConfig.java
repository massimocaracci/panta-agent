package com.pantasoft.simpleagent.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Value("${deepseek.api.key}")
    private String deepseekApiKey;

    @Value("${deepseek.api.url}")
    private String deepseekApiUrl;

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.createDefault();
    }

    public String getDeepseekApiKey() {
        return deepseekApiKey;
    }

    public String getDeepseekApiUrl() {
        return deepseekApiUrl;
    }
} 