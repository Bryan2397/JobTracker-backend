package com.job.tracker.controller;

import com.job.tracker.entity.Job;
import com.job.tracker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/save")
    public String saveJob(@RequestBody Job job, Authentication authentication){
        return jobService.saveJob(job, authentication);
    }

    @GetMapping("/myJobs")
    public List<Job> myJobs(Authentication authentication){
        return jobService.getJobs(authentication);

    }
}
