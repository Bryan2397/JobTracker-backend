package com.job.tracker.service;

import com.job.tracker.CustomUserDetails;
import com.job.tracker.dao.JobRepository;
import com.job.tracker.dao.UserRepository;
import com.job.tracker.entity.Job;
import com.job.tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

    public String saveJob(Job job, Authentication authentication){

        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return "User doesn't exist";
        }

        job.setUser(user);

        jobRepository.save(job);

        return "Successful";
    }

    public List<Job> getJobs(Authentication authentication){
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getUserId());
        if(user == null){
            return new ArrayList<>();
        }
        List<Job> myJobs = jobRepository.findByUser_Id(user.getId());
        for(Job j : myJobs){
            j.setUser(null);
        }

        return myJobs;
    }
}
