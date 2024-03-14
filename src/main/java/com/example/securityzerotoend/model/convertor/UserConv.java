package com.example.securityzerotoend.model.convertor;

import com.example.securityzerotoend.model.dto.RegisterRequest;
import com.example.securityzerotoend.model.entity.UserEntity;

public class UserConv {
    public UserEntity registerConvertToEntity(RegisterRequest registerRequest){
        if ( registerRequest == null ) {
            return null;
        }
        UserEntity user = new UserEntity();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRole(registerRequest.getRole());

        return user;
    }
}
