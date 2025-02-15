package com.example.backend.controller;

import com.example.backend.domain.service.BookService;
import com.example.backend.domain.service.BorrowRecordService;
import com.example.backend.domain.service.UserService;
import com.example.backend.infra.mapper.BookMapper;
import com.example.backend.infra.mapper.BorrowRecordMapper;
import com.example.backend.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
abstract class ControllerTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @MockitoBean
    protected BookService bookService;

    @MockitoBean
    protected UserService userService;

    @MockitoBean
    protected BorrowRecordService borrowRecordService;

    @MockitoBean
    private BookMapper bookMapper;

    @MockitoBean
    private BorrowRecordMapper borrowRecordMapper;

    @MockitoBean
    private UserMapper userMapper;
}
