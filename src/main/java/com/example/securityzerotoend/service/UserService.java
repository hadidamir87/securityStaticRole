package com.example.securityzerotoend.service;

import com.example.securityzerotoend.exceptionhandlers.exceptions.ServiceException;
import com.example.securityzerotoend.model.dto.LoginRequest;
import com.example.securityzerotoend.model.entity.Role;
import com.example.securityzerotoend.model.entity.UserEntity;
import com.example.securityzerotoend.model.response.AuthenticationResponse;
import com.example.securityzerotoend.repository.UserRepository;
import com.example.securityzerotoend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<UserEntity, UserRepository>{

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    public ResponseEntity<UserEntity> register(UserEntity user) throws Exception {
        if (repository.existsByUsername(user.getUsername())) {
            throw new ServiceException("username-duplicated");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(user.getRole());
        repository.save(user);
        return ResponseEntity.ok(user);
    }
    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        var user = repository.findByUsername(loginRequest.getUsername()).orElseThrow();
        final String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(token).build();
    }
    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new ServiceException(e, "user-disabled");
        } catch (BadCredentialsException e) {
            throw new ServiceException(e, "invalid-credentials");
        }
    }
    @Override
    public UserEntity updateById(UserEntity userEntity) throws Exception {
        return null;
    }
}
