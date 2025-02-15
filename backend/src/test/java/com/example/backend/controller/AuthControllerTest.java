package com.example.backend.controller;

import com.example.backend.domain.entity.User;
import com.example.backend.infra.security.SecurityConfig;
import com.example.backend.utils.InternalServerException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest extends ControllerTestBase {

    @Test
    void register_success() throws Exception {
        doNothing().when(userService).insert(any());

        var requestBody = """
                {
                    "name": "user1",
                    "email": "user1@email.com"
                }
                """;

        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                                .with(csrf())
                )
                .andExpect(status().isNoContent());

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).insert(captor.capture());
        User capturedUser = captor.getValue();

        assertEquals("user1", capturedUser.getUsername());
        assertEquals("user1@email.com", capturedUser.getEmail());
    }

    @Test
    void register_fail_databaseError() throws Exception {
        doThrow(new InternalServerException("Database error")).when(userService).insert(any());

        var requestBody = """
                {
                    "name": "user1",
                    "email": "user1@email.com"
                }
                """;

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .with(csrf()))
                .andExpect(status().isInternalServerError());

    }

    @Test
    void register_fail_requestBodyError() throws Exception {
        doNothing().when(userService).insert(any());
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().isBadRequest());
        verify(userService, times(0)).insert(any());
    }
}
