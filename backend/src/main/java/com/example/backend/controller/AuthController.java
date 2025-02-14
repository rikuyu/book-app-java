package com.example.backend.controller;

import com.example.backend.domain.entity.LoginRequest;
import com.example.backend.domain.entity.User;
import com.example.backend.domain.service.UserService;
import com.example.backend.utils.InternalServerException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Profile("stg")
@RestController
public class AuthController {

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    private final AuthenticationManager authenticationManager;
    private final UserService service;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserService service
    ) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody @NotNull LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var token = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.id(), loginRequest.password());
        var auth = authenticationManager.authenticate(token);

        if (auth.isAuthenticated()) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            securityContextRepository.saveContext(context, request, response);

            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody @NotNull User user) {
        try {
            service.insert(user);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }
}
