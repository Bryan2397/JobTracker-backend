package com.job.tracker.service;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.dao.JobRepository;
import com.job.tracker.dao.UserRepository;
import com.job.tracker.dto.DeleteJobIds;
import com.job.tracker.entity.Job;
import com.job.tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {

    JobRepository jobRepository;
    UserRepository userRepository;

    @Autowired
    public JobService(JobRepository jobRepository, UserRepository userRepository){
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> saveJob(Job job, Authentication authentication){

        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return ResponseEntity.ok("User doesn't exist");
        }

        job.setUser(user);
        job.setDateAdded(LocalDate.now());
        jobRepository.save(job);

        return ResponseEntity.ok("Successful");
    }

    public List<Job> getJobs(Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return new ArrayList<>();
        }

        List<Job> myJobs = jobRepository.findByUser_Id(user.getId());
        for(Job job : myJobs){
            job.setUser(null);
        }

        return myJobs;
    }

    public Job getJobById(int id, Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return null;
        }
        return jobRepository.findById(id).get();
    }

    public void deleteJob(int id, Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return;
        }
        jobRepository.deleteById(id);
    }

    public void deleteJobs(DeleteJobIds jobs, Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return;
        }

        List<Integer> ids = jobs.getJob_ids();
        for(int id : ids){
            jobRepository.deleteById(id);
        }
    }



    public void updateJob(int id, Job job, Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return;
        }
        Job existingJob = jobRepository.findById(id).orElse(null);
        if(existingJob == null){
            return;
        }
        if(existingJob.getUser().getId() != user.getId()){
            throw new RuntimeException("Error updating");
        }


        existingJob.setStatus(job.getStatus());
        existingJob.setCompany(job.getCompany());
        existingJob.setTitle(job.getTitle());
        existingJob.setNote(job.getNote());
        existingJob.setLocation(job.getLocation());
        existingJob.setJobSummary(job.getJobSummary());
        existingJob.setSalary(job.getSalary());
        existingJob.setJobUrl(job.getJobUrl());
        existingJob.setDateApplied(job.getDateApplied());
        existingJob.setSkills(job.getSkills());

        jobRepository.save(existingJob);
    }
}
