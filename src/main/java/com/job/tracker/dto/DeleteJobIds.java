package com.job.tracker.dto;

import java.util.List;

public class DeleteJobIds {
    List<Integer> job_ids;

    public void setJob_ids(List<Integer> job_ids) {
        this.job_ids = job_ids;
    }

    public List<Integer> getJob_ids() {
        return job_ids;
    }
}
