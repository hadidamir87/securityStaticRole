package com.example.securityzerotoend.model.dto;

import com.example.securityzerotoend.model.entity.Role;
import lombok.Data;

@Data

public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

}
