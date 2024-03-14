package com.example.securityzerotoend.model.response;

import com.example.securityzerotoend.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
//    private UserEntity user;
    private String token;
}
