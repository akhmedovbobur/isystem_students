package com.example.isystem_students.util;

import com.example.isystem_students.application.CustomUserDetails;
import com.example.isystem_students.exception.ServerBadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Integer getUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServerBadRequestException("class SecurityUtil -> Error");
        }
    }

    public static String get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
