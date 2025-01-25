package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    Book mockBook1 = new Book("book1");
    Book mockBook2 = new Book("book2");
    List<Book> mockBooks = Arrays.asList(mockBook1, mockBook2);

    @Test
    void getBook() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void addBook() {
    }

    @Test
    void deleteBookById() {
    }
}