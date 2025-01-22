package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.User;
import com.example.bookapp.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    private UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.findAllUsers();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        service.insert(user);
    }

    @PutMapping("/name")
    public void updateUserName(@RequestBody User user) {
        service.updateName(user);
    }

    @PutMapping("/email")
    public void updateUserEmail(@RequestBody User user) {
        service.updateEmail(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id) {
        if(service.deleteById(id)) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }
}
