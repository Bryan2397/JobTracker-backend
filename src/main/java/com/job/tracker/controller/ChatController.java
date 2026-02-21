package com.job.tracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.job.tracker.dto.JobResponse;
import com.job.tracker.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/chat")
public class ChatController {

    @Autowired
    private ChatGPTService chatGPTService;

    @PostMapping ("/description")
    public ResponseEntity<JobResponse> chat(@RequestBody Map<String, Object> body) throws JsonProcessingException {
        Map<String, String> params = (Map<String, String>) body.get("params");
        String description = params.get("description");
        System.out.println(description.length());
        String newPrompt = "Extract the following fields from this job description and return ONLY valid JSON, Do not include markdown formatting.\n" +
                "Do not wrap the JSON in triple backticks.: {" +
                "title: string;"+
        "company: string;"+
        "jobSummary: string;"+
        "location?: string;"+
                "salary?: string"+
                "skills: string[] }"+
             description;
        return ResponseEntity.ok(chatGPTService.getChatResponse(newPrompt));
    }
}
