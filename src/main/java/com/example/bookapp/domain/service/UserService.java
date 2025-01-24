package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.User;
import com.example.bookapp.infra.mapper.BorrowRecordMapper;
import com.example.bookapp.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final BorrowRecordMapper borrowRecordMapper;

    @Autowired
    public UserService(
            UserMapper userMapper,
            BorrowRecordMapper borrowRecordMapper
    ) {
        this.userMapper = userMapper;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User findById(int id) {
        return userMapper.findById(id);
    }

    public void insert(User user) {
        userMapper.insert(user);
    }

    @Transactional
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }
}
