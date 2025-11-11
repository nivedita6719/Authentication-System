package com.authentication.controller;

import com.authentication.dto.UserProfileResponse;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<UserProfileResponse> getProfile() {
        try {
            UserProfileResponse profile = userService.getProfile();
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}



