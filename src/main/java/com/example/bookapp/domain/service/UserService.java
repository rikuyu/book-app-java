package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.User;
import com.example.bookapp.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper mapper;

    @Autowired
    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public List<User> findAllUsers() {
        return mapper.findAllUsers();
    }

    public User findById(int id) {
        return mapper.findById(id);
    }

    public void insert(User user) {
        mapper.insert(user);
    }

    public void updateEmail(User user) {
        mapper.updateEmail(user);
    }

    public void updateName(User user) {
        mapper.updateName(user);
    }

    public void deleteById(int id) {
        mapper.deleteById(id);
    }
}
