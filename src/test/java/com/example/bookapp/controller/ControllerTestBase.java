package com.example.bookapp.controller;

import com.example.bookapp.domain.service.BookService;
import com.example.bookapp.domain.service.BorrowRecordService;
import com.example.bookapp.domain.service.UserService;
import com.example.bookapp.infra.mapper.BookMapper;
import com.example.bookapp.infra.mapper.BorrowRecordMapper;
import com.example.bookapp.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

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
