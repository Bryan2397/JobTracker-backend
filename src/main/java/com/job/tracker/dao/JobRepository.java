package com.job.tracker.dao;

import com.job.tracker.entity.Job;
import com.job.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<User, Long> {
}
