package com.authentication.service;

import com.authentication.dto.DashboardResponse;
import com.authentication.dto.UserProfileResponse;
import com.authentication.entity.User;
import com.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public UserProfileResponse getProfile() {
        User user = getCurrentUser();
        return convertToProfileResponse(user);
    }
    
    public DashboardResponse getDashboard() {
        User user = getCurrentUser();
        DashboardResponse response = new DashboardResponse();
        response.setWelcomeMessage("Welcome to your dashboard, " + user.getFirstName() + "!");
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setLastLogin(LocalDateTime.now());
        response.setStatus("Active");
        return response;
    }
    
    private UserProfileResponse convertToProfileResponse(User user) {
        UserProfileResponse response = new UserProfileResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }
}



