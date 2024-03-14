package com.example.securityzerotoend.rest;

import com.example.securityzerotoend.model.dto.RegisterRequest;
import com.example.securityzerotoend.model.entity.UserEntity;
import com.example.securityzerotoend.model.response.RegisterResponse;
import com.example.securityzerotoend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preAutho")
public class RestOfRest extends AbstractController<UserEntity, RegisterRequest, RegisterResponse, UserService> {
    @GetMapping("/gett")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hale");
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_Admin')")
//    @PreAuthorize(value = "hasRole('Admin')")
//    @Secured("ROLE_Admin")
    public ResponseEntity<String> testForAuthority(){
        return ResponseEntity.ok("authority ok di");
    }
}
