package com.job.tracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.job.tracker.CustomUserDetails;
import com.job.tracker.dao.UserRepository;
import com.job.tracker.dto.ChatgptJobResponse;
import com.job.tracker.entity.User;
import com.job.tracker.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/chat")
public class ChatController {

    private ChatGPTService chatGPTService;
    private UserRepository userRepository;

    @Autowired
    public ChatController(ChatGPTService chatGPTService, UserRepository userRepository){
        this.chatGPTService = chatGPTService;
        this.userRepository = userRepository;
    }

    @PostMapping ("/description")
    public ResponseEntity<ChatgptJobResponse> chat(@RequestBody Map<String, Object> body, Authentication authentication) throws JsonProcessingException {
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user.getAiUsage() == 0){
            return null;
        }
        Map<String, String> params = (Map<String, String>) body.get("params");
        String description = params.get("description");
        String newPrompt = "Extract the following fields from this job description and return ONLY valid JSON. Reject anything that isnt a job description, give a simple error response.  Do not include markdown formatting.\n" +
                "Do not wrap the JSON in triple backticks.: {, also add whether the location is remote, onsite, or hybrid in the same field as location." +
                "title: string;"+
        "company: string;"+
        "jobSummary: string;"+
        "location?: string;"+
                "salary?: string"+
                "skills: string[] these are the languages/frameworks/tools needed }, reject anything you feel isnt a job description with a few words of the failure"+
             description;
        userRepository.decrementAiUsage(user.getId());
        User user1 = userRepository.findById(user.getId());
        if(user1.getAiUsage() == 0){
            userRepository.setAiWaitTime(user.getId());
        }
        return ResponseEntity.ok(chatGPTService.getChatResponse(newPrompt));
    }
}
