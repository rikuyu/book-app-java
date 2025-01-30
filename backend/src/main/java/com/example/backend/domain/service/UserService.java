package com.example.backend.domain.service;

import com.example.backend.domain.entity.User;
import com.example.backend.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserMapper mapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.mapper = userMapper;
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

    @Transactional
    public int deleteById(int id) {
        return mapper.deleteById(id);
    }
}
