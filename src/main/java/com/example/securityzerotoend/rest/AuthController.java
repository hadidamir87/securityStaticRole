package com.example.securityzerotoend.rest;

import com.example.securityzerotoend.model.dto.LoginRequest;
import com.example.securityzerotoend.model.dto.RegisterRequest;
import com.example.securityzerotoend.model.entity.UserEntity;
import com.example.securityzerotoend.model.response.AuthenticationResponse;
import com.example.securityzerotoend.model.response.RegisterResponse;
import com.example.securityzerotoend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends AbstractController<UserEntity, RegisterRequest, RegisterResponse, UserService> {

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest dto) throws Exception {
        return ResponseEntity.ok(service.register(convertor.convertDto(dto)));
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(service.login(loginRequest));
    }


   /* @PostMapping("/admin-register")
    @Transactional
    public ResponseEntity<?> registerAdmin(@Validated @RequestBody RegisterDto dto) throws Exception {
        return ResponseEntity.ok(service.registerAdmin(convertor.convertDto(dto)));
    }*/
}
