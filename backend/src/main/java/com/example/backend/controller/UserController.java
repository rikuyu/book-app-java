package com.example.backend.controller;

import com.example.backend.domain.entity.User;
import com.example.backend.domain.service.UserService;
import com.example.backend.utils.InternalServerException;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(service.findAllUsers());
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable @Positive int id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable @Positive int id) {
        try {
            int affectedRows = service.deleteById(id);
            if (affectedRows == 0) {
                throw new InternalServerException("user not found");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMe(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, Object> me = Map.of(
                "id", user.getId(),
                "name", user.getUsername(),
                "email", user.getEmail()
        );
        return ResponseEntity.ok(me);
    }
}
