package com.pantasoft.simpleagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class PantaAgentAiApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PantaAgentAiApplication.class);
        // Set default profile to local if no profile is specified
        app.setDefaultProperties(java.util.Collections.singletonMap("spring.profiles.default", "local"));
        app.run(args);
    }
} 