package com.authentication.controller;

import com.authentication.dto.DashboardResponse;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard() {
        try {
            DashboardResponse dashboard = userService.getDashboard();
            return ResponseEntity.ok(dashboard);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}



