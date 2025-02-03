package com.example.backend.controller;

import com.example.backend.domain.entity.User;
import com.example.backend.domain.service.UserService;
import com.example.backend.utils.BadRequestException;
import com.example.backend.utils.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    private UserController(UserService service) {
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

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        if (user == null) {
            throw new BadRequestException("user is null");
        }
        try {
            service.insert(user);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        if (id <= 0) {
            throw new BadRequestException("id must be greater than 0");
        }

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        if (id <= 0) {
            throw new BadRequestException("id must be greater than 0");
        }

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
}
