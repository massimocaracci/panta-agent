package com.pantasoft.simpleagent.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {
    private String id;
    private String name;
    private String status;
    private String currentTask;
    private String llmResponse;
    
    public void performTask(String task) {
        this.currentTask = task;
        this.status = "BUSY";
    }
    
    public void completeTask() {
        this.currentTask = null;
        this.status = "IDLE";
    }

    public void setLLMResponse(String response) {
        this.llmResponse = response;
    }
} 