package com.example.backend.domain.service;

import com.example.backend.domain.entity.User;
import com.example.backend.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.mapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return mapper.findAllUsers();
    }

    public User findById(int id) {
        return mapper.findById(id);
    }

    public void insert(User user) {
        user.setEncodedPassword(passwordEncoder.encode(user.getPassword()));
        mapper.insert(user);
    }

    @Transactional
    public int deleteById(int id) {
        return mapper.deleteById(id);
    }
}
