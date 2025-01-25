package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.BorrowRecord;
import com.example.bookapp.domain.service.BorrowRecordService;
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
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BorrowRecordController.class)
class BorrowRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BorrowRecordService service;

    @MockitoBean
    private BookMapper bookMapper;

    @MockitoBean
    private BorrowRecordMapper borrowRecordMapper;

    @MockitoBean
    private UserMapper userMapper;

    BorrowRecord mockBorrowRecord1 = new BorrowRecord(1, 1, 1, null, null);
    BorrowRecord mockBorrowRecord2 = new BorrowRecord(2, 2, 2, null, null);

    @Test
    void getBorrowRecord() throws Exception {
        when(service.findAllBorrowRecords()).thenReturn(Arrays.asList(mockBorrowRecord1, mockBorrowRecord2));
        mockMvc.perform(get("/borrow_record"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                [
                                    {
                                        "id": 1,
                                        "user_id": 1,
                                        "book_id": 1,
                                        "borrowed_date": null,
                                        "returned_date": null
                                    },
                                    {
                                        "id": 2,
                                        "user_id": 2,
                                        "book_id": 2,
                                        "borrowed_date": null,
                                        "returned_date": null
                                    }
                                ]
                        """));

        verify(service, times(1)).findAllBorrowRecords();
    }

    @Test
    void getBookRecordsByUserId() throws Exception {
        when(service.findByUserId(1)).thenReturn(Collections.singletonList(mockBorrowRecord1));
        mockMvc.perform(get("/borrow_record/user").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                [
                                    {
                                        "id": 1,
                                        "user_id": 1,
                                        "book_id": 1,
                                        "borrowed_date": null,
                                        "returned_date": null
                                    }
                                ]
                        """));
        verify(service, times(1)).findByUserId(1);
    }

    @Test
    void getBookRecordsByBookId() throws Exception {
        when(service.findByBookId(1)).thenReturn(Collections.singletonList(mockBorrowRecord1));
        mockMvc.perform(get("/borrow_record/book").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                [
                                    {
                                        "id": 1,
                                        "user_id": 1,
                                        "book_id": 1,
                                        "borrowed_date": null,
                                        "returned_date": null
                                    }
                                ]
                        """));
        verify(service, times(1)).findByBookId(1);
    }

    @Test
    void borrowBook() throws Exception {
        when(service.insertBorrowRecordIfAvailable(any())).thenReturn(true);

        var requestBody = """
                {
                    "user_id": 1,
                    "book_id": 1
                }
                """;

        mockMvc.perform(
                post("/borrow_record")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(service, times(1)).insertBorrowRecordIfAvailable(any());
    }

    @Test
    void returnBook_success() throws Exception {
        when(service.returnBook(1, 1)).thenReturn(true);
        mockMvc.perform(
                put("/borrow_record/{borrow_record}/book/{book_id}", 1, 1)
        ).andExpect(status().isOk());

        verify(service, times(1)).returnBook(1, 1);
    }

    @Test
    void returnBook_fail_bookId() throws Exception {
        when(service.returnBook(1, 1)).thenReturn(true);
        mockMvc.perform(
                put("/borrow_record/{borrow_record}/book/{book_id}", 0, 1)
        ).andExpect(status().isBadRequest());

        verify(service, times(0)).returnBook(0, 1);
    }
}