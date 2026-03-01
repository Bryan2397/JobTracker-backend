package com.job.tracker.dao;

import com.job.tracker.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByUser_Id(Integer id);
}
