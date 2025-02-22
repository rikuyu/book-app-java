package com.example.backend.controller;

import com.example.backend.domain.entity.Role;
import com.example.backend.domain.entity.User;
import com.example.backend.infra.security.SecurityConfig;
import com.example.backend.utils.InternalServerException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
@ActiveProfiles("stg")
public class AuthControllerTest extends ControllerTestBase {

    @MockitoBean
    private SecurityContextRepository repository;

    @MockitoBean
    private AuthenticationManager authManager;

    private final User mockUser = new User(2, "Kenta", "kenta@email.com", "pw456", Role.USER);

    @Test
    void login_success() throws Exception {
        var auth = new UsernamePasswordAuthenticationToken(
                mockUser.getId(),
                mockUser.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        when(authManager.authenticate(any())).thenReturn(auth);
        doNothing().when(repository).saveContext(any(), any(), any());


        var requestBody = """
                {
                    "id": "2",
                    "password": "pw456"
                }
                """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(authManager, times(1)).authenticate(any());
        verify(repository, times(1)).saveContext(any(), any(), any());
    }

    @Test
    void login_fail_invalid_credentials() throws Exception {
        when(authManager.authenticate(any())).thenThrow(new BadCredentialsException(""));
        doNothing().when(repository).saveContext(any(), any(), any());


        var requestBody = """
                {
                    "id": "2",
                    "password": "pw456"
                }
                """;

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .with(csrf()))
                .andExpect(status().isForbidden());

        verify(authManager, times(1)).authenticate(any());
        verify(repository, times(0)).saveContext(any(), any(), any());
    }

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
