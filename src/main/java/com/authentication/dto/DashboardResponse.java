package com.authentication.dto;

import com.authentication.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private String welcomeMessage;
    private String username;
    private String email;
    private User.Role role;
    private LocalDateTime lastLogin;
    private String status;
}

