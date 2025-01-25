package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.User;
import com.example.bookapp.domain.service.UserService;
import com.example.bookapp.infra.mapper.BookMapper;
import com.example.bookapp.infra.mapper.BorrowRecordMapper;
import com.example.bookapp.infra.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @MockitoBean
    private BookMapper bookMapper;

    @MockitoBean
    private BorrowRecordMapper borrowRecordMapper;

    @MockitoBean
    private UserMapper userMapper;

    private final User mockUser1 = new User(1, "user1", "user1@email.com");
    private final User mockUser2 = new User(2, "user2", "user2@email.com");

    @Test
    void getAllUsers() throws Exception {
        when(service.findAllUsers()).thenReturn(Arrays.asList(mockUser1, mockUser2));
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "name": "user1",
                                "email": "user1@email.com"
                            },
                            {
                                "id": 2,
                                "name": "user2",
                                "email": "user2@email.com"
                            }
                        ]
                        """
                ));
        verify(service, times(1)).findAllUsers();
    }

    @Test
    void createUser() throws Exception {
        doNothing().when(service).insert(any());

        var requestBody = """
                {
                    "name": "user1",
                    "email": "user1@email.com"
                }
                """;

        mockMvc.perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk());

        verify(service, times(1)).insert(any());
    }

    @Test
    void getUserById() throws Exception {
        when(service.findById(1)).thenReturn(mockUser1);
        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(
                        content().json("""
                                {
                                        "id": 1,
                                        "name": "user1",
                                        "email": "user1@email.com"
                                    }
                                """
                        )
                );
        verify(service, times(1)).findById(1);
    }

    @Test
    void deleteUserById() throws Exception {
        when(service.deleteById(1)).thenReturn(1);
        mockMvc.perform(delete("/user/{id}", 1)).andExpect(status().isOk());
        verify(service, times(1)).deleteById(1);
    }
}