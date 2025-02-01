package com.example.backend.controller;

import com.example.backend.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

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
class UserControllerTest extends ControllerTestBase {

    private final User mockUser1 = new User(1, "user1", "user1@email.com");
    private final User mockUser2 = new User(2, "user2", "user2@email.com");

    @Test
    void getAllUsers_success() throws Exception {
        when(userService.findAllUsers()).thenReturn(Arrays.asList(mockUser1, mockUser2));
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
        verify(userService, times(1)).findAllUsers();
    }

    @Test
    void createUser_success() throws Exception {
        doNothing().when(userService).insert(any());

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
                .andExpect(status().isNoContent());

        verify(userService, times(1)).insert(any());
    }

    @Test
    void getUserById_success() throws Exception {
        when(userService.findById(1)).thenReturn(mockUser1);
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
        verify(userService, times(1)).findById(1);
    }

    @Test
    void getUserById_fail() throws Exception {
        when(userService.findById(1)).thenReturn(mockUser1);
        mockMvc.perform(get("/user/{id}", 0))
                .andExpect(status().isBadRequest());
        verify(userService, times(0)).findById(1);
    }


    @Test
    void deleteUserById_success() throws Exception {
        when(userService.deleteById(1)).thenReturn(1);
        mockMvc.perform(delete("/user/{id}", 1)).andExpect(status().isNoContent());
        verify(userService, times(1)).deleteById(1);
    }

    @Test
    void deleteUserById_fail() throws Exception {
        when(userService.deleteById(1)).thenReturn(1);
        mockMvc.perform(delete("/user/{id}", 0)).andExpect(status().isBadRequest());
        verify(userService, times(0)).deleteById(1);
    }
}