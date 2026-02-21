package com.job.tracker.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "jobs")
public class Job {
    public enum Status { APPLIED, OA, PHONE, TECHNICAL, OFFER, REJECTED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.APPLIED;

    @Column(name = "company")
    private String company;

    @Column(name = "last_status_change")
    private Date lastStatusChangeAt;


    @Column(name = "job_url", length = 100)
    private String jobUrl;


    @Column(name = "added_on")
    private Date addedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
