package com.job.tracker.utility;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


// to get the current user from the SecurityContext
public class SecurityUtils {

    public String getCurrentUserEmail(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
