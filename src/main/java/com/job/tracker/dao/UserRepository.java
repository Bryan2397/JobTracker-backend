package com.job.tracker.dao;

import com.job.tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.aiUsage = u.aiUsage - 1 WHERE u.id = :id")
    int decrementAiUsage(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.aiUsage = 5 WHERE u.id = :id")
    int resetAiUsage(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.aiResetDate = NOW() WHERE u.id = :id")
    int setAiWaitTime(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.aiResetDate = NULL WHERE u.id = :id")
    int clearAiWaitTime(@Param("id") Integer id);

}
