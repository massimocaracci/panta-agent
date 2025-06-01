package com.pantasoft.simpleagent.controller;

import com.pantasoft.simpleagent.model.Agent;
import com.pantasoft.simpleagent.service.AgentAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@Tag(name = "Agent AI Management", description = "APIs for managing AI agents with DeepSeek integration")
public class AgentAiController {

    private final AgentAiService agentAiService;

    @Autowired
    public AgentAiController(AgentAiService agentAiService) {
        this.agentAiService = agentAiService;
    }

    @Operation(summary = "Create a new agent", description = "Creates a new AI agent with the specified name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agent created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Agent> createAgent(
            @Parameter(description = "Name of the agent") @RequestParam String name) {
        return ResponseEntity.ok(agentAiService.createAgent(name));
    }

    @Operation(summary = "Get agent by ID", description = "Retrieves an agent by their unique identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agent found"),
        @ApiResponse(responseCode = "404", description = "Agent not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgent(
            @Parameter(description = "ID of the agent") @PathVariable String id) {
        Agent agent = agentAiService.getAgent(id);
        return agent != null ? ResponseEntity.ok(agent) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all agents", description = "Retrieves a list of all agents")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of agents retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Agent>> getAllAgents() {
        return ResponseEntity.ok(agentAiService.getAllAgents());
    }

    @Operation(summary = "Assign task to agent", description = "Assigns a task to an agent and gets LLM response")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task assigned successfully"),
        @ApiResponse(responseCode = "404", description = "Agent not found")
    })
    @PostMapping("/{id}/tasks")
    public ResponseEntity<Agent> assignTask(
            @Parameter(description = "ID of the agent") @PathVariable String id,
            @Parameter(description = "Task to be performed") @RequestParam String task) {
        Agent agent = agentAiService.assignTask(id, task);
        return agent != null ? ResponseEntity.ok(agent) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Complete agent's task", description = "Marks the current task of an agent as complete")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task completed successfully"),
        @ApiResponse(responseCode = "404", description = "Agent not found")
    })
    @PostMapping("/{id}/complete")
    public ResponseEntity<Agent> completeTask(
            @Parameter(description = "ID of the agent") @PathVariable String id) {
        Agent agent = agentAiService.completeTask(id);
        return agent != null ? ResponseEntity.ok(agent) : ResponseEntity.notFound().build();
    }
} 