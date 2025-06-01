package com.pantasoft.simpleagent.service;

import com.pantasoft.simpleagent.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgentAiService {
    private final Map<String, Agent> agents = new ConcurrentHashMap<>();
    private final LLMService llmService;

    @Autowired
    public AgentAiService(LLMService llmService) {
        this.llmService = llmService;
    }

    public Agent createAgent(String name) {
        Agent agent = new Agent();
        agent.setId(String.valueOf(System.currentTimeMillis()));
        agent.setName(name);
        agent.setStatus("IDLE");
        agents.put(agent.getId(), agent);
        return agent;
    }

    public Agent getAgent(String id) {
        return agents.get(id);
    }

    public List<Agent> getAllAgents() {
        return new ArrayList<>(agents.values());
    }

    public Agent assignTask(String agentId, String task) {
        Agent agent = agents.get(agentId);
        if (agent != null) {
            agent.performTask(task);
            // Get LLM response for the task
            String llmResponse = llmService.getCompletion(task);
            agent.setLLMResponse(llmResponse);
        }
        return agent;
    }

    public Agent completeTask(String agentId) {
        Agent agent = agents.get(agentId);
        if (agent != null) {
            agent.completeTask();
        }
        return agent;
    }
} 