package com.project.yjshop.facade;

import com.project.yjshop.domain.user.User;
import com.project.yjshop.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public User getCurrentTeacher() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((AuthDetails) principal).getUser();
    }

}
