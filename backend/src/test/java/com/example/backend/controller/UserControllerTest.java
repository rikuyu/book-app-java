package com.example.backend.controller;

import com.example.backend.domain.entity.Role;
import com.example.backend.domain.entity.User;
import com.example.backend.utils.InternalServerException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTestBase {

    private final User mockUser1 = new User(1, "user1", "user1@email.com", "pw1", Role.USER);
    private final User mockUser2 = new User(2, "user2", "user2@email.com", "pw2", Role.USER);

    @Test
    void getAllUsers_success() throws Exception {
        when(userService.findAllUsers()).thenReturn(Arrays.asList(mockUser1, mockUser2));
        mockMvc.perform(get("/users"))
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
    void getUserById_success() throws Exception {
        when(userService.findById(1)).thenReturn(mockUser1);
        mockMvc.perform(get("/users/{id}", 1))
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
        when(userService.findById(anyInt())).thenThrow(new InternalServerException("user not found"));
        mockMvc.perform(get("/users/{id}", 999))
                .andExpect(status().isInternalServerError());
        verify(userService, times(1)).findById(999);
    }

    @Test
    void deleteUserById_success() throws Exception {
        when(userService.deleteById(1)).thenReturn(1);
        mockMvc.perform(delete("/users/{id}", 1).with(csrf())).andExpect(status().isNoContent());
        verify(userService, times(1)).deleteById(1);
    }

    @Test
    void deleteUserById_fail() throws Exception {
        when(userService.deleteById(anyInt())).thenReturn(0);
        mockMvc.perform(delete("/users/{id}", 1).with(csrf()))
                .andExpect(status().isInternalServerError());
        verify(userService, times(1)).deleteById(1);
    }

    @Test
    void getMe_success() throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                mockUser1,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
        mockMvc.perform(get("/users/me").with(SecurityMockMvcRequestPostProcessors.authentication(auth)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(mockUser1.getUsername())))
                .andExpect(jsonPath("$.email", is(mockUser1.getEmail())))
                .andExpect(jsonPath("$.isAdmin", is(mockUser1.isAdmin())));
    }

    @Test
    void getMe_fail() throws Exception {
        mockMvc.perform(get("/users/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void uploadImage_success() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.png", "image/png", "test data".getBytes());
        mockMvc.perform(multipart("/users/image").file(file).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void uploadImage_fail_emptyFile() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "", "image/png", new byte[0]);
        mockMvc.perform(multipart("/users/image").file(file).with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void uploadImage_fail_invalidFormat() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "file.txt", "text/plain", "invalid".getBytes());
        mockMvc.perform(multipart("/users/image").file(file).with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void uploadImage_fail_largeFile() throws Exception {
        byte[] largeData = new byte[6 * 1024 * 1024]; // 6MB
        MockMultipartFile file = new MockMultipartFile("file", "large.png", "image/png", largeData);
        mockMvc.perform(multipart("/users/image").file(file).with(csrf()))
                .andExpect(status().isBadRequest());
    }
}
