package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.entity.Status;
import com.example.bookapp.domain.service.BookService;
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

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService service;

    @MockitoBean
    private BookMapper bookMapper;

    @MockitoBean
    private BorrowRecordMapper borrowRecordMapper;

    @MockitoBean
    private UserMapper userMapper;

    Book mockBook1 = new Book(1, "book1", Status.AVAIlABLE);
    Book mockBook2 = new Book(2, "book2", Status.AVAIlABLE);

    @Test
    void getBook() throws Exception {
        when(service.findAllBooks()).thenReturn(Arrays.asList(mockBook1, mockBook2));
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "title": "book1",
                                "status": "AVAIlABLE"
                            },
                            {
                                "id": 2,
                                "title": "book2",
                                "status": "AVAIlABLE"
                            }
                        ]
                        """
                ));
        verify(service, times(1)).findAllBooks();
    }

    @Test
    void getBookById() throws Exception {
        when(service.findById(1)).thenReturn(mockBook1);
        mockMvc.perform(get("/book/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "title": "book1",
                            "status": "AVAIlABLE"
                        }
                        """
                ));
        verify(service, times(1)).findById(1);
    }

    @Test
    void addBook() throws Exception {
        doNothing().when(service).insertBook(any());
        var requestBody = """
                {
                    "title": "test"
                }
                """;

        mockMvc.perform(
                post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isNoContent());
        verify(service, times(1)).insertBook(any());
    }

    @Test
    void deleteBookById() throws Exception {
        when(service.deleteById(1)).thenReturn(1);
        mockMvc.perform(delete("/book/{id}", 1)).andExpect(status().isNoContent());
        verify(service, times(1)).deleteById(1);
    }
}