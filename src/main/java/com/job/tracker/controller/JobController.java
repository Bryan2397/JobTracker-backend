package com.job.tracker.controller;

import com.job.tracker.dto.DeleteJobIds;
import com.job.tracker.entity.Job;
import com.job.tracker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping("/save")
    public String saveJob(@RequestBody Job job, Authentication authentication){
        return jobService.saveJob(job, authentication);
    }

    @GetMapping("/myJobs")
    public List<Job> myJobs(Authentication authentication){
        return jobService.getJobs(authentication);
    }

    @DeleteMapping("/delete")
    public void deleteJobs(@RequestBody DeleteJobIds jobs, Authentication authentication){
        jobService.deleteJobs(jobs, authentication);
    }

    @PutMapping("/{id}")
    public void updateJob(@PathVariable Integer id, @RequestBody Job job, Authentication authentication){
        jobService.updateJob(id, job, authentication);
    }
}
