package com.job.tracker.controller;

import com.job.tracker.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/job")
public class JobController {
    private JobService jobService;

}
