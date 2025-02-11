package com.example.backend.domain.service;

import com.example.backend.domain.entity.User;
import com.example.backend.infra.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    public AuthUserDetailService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        var userId = Integer.parseInt(id);
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("user" + id + "not found");
        }
        return user;
    }
}
