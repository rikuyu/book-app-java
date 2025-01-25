package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.User;
import com.example.bookapp.infra.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserMapper mapper;

    private final User mockUser1 = new User("user1", "user1@email.com");
    private final User mockUser2 = new User("user2", "user2@email.com");
    private final List<User> mockUsers = Arrays.asList(mockUser1, mockUser2);

    @Test
    void findAllUsers() {
        when(mapper.findAllUsers()).thenReturn(mockUsers);
        List<User> users = service.findAllUsers();

        assertEquals(mockUsers.size(), users.size());
        assertEquals(mockUser1.name, users.get(0).name);

        verify(mapper).findAllUsers();
    }

    @Test
    void findById() {
        when(mapper.findById(1)).thenReturn(mockUser1);
        User user = service.findById(1);

        assertEquals(mockUser1.name, user.name);
        assertEquals(mockUser1.email, user.email);

        verify(mapper).findById(1);
    }

    @Test
    void insert() {
        doNothing().when(mapper).insert(any());
        service.insert(mockUser1);
        verify(mapper).insert(any());
    }

    @Test
    void deleteById() {
        when(mapper.deleteById(1)).thenReturn(1);
        int affectedRows = service.deleteById(1);
        assertEquals(1, affectedRows);
        verify(mapper).deleteById(1);
    }
}