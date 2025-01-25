package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.entity.Status;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BookMapperTest {

    @Autowired
    private BookMapper mapper;


    @Test
    void findAllBooks() {
        List<Book> books = mapper.findAllBooks();
        Book firstBook = books.get(0);

        assertEquals(3, books.size());
        assertEquals(1, firstBook.id);
        assertEquals("Ruby on Rails Test Book", firstBook.title);
        assertEquals(Status.AVAIlABLE, firstBook.status);
    }

    @Test
    void insertBook() {
        Book newBook = new Book("Test Book");
        mapper.insertBook(newBook);

        List<Book> books = mapper.findAllBooks();
        var insertedBook = books.get(3);

        assertEquals(4, books.size());
        assertEquals(4, insertedBook.id);
        assertEquals("Test Book", insertedBook.title);
    }

    @Test
    void findById() {
        Book book = mapper.findById(2);

        assertEquals(2, book.id);
        assertEquals("Golang Web Server Tutorial", book.title);
    }

    @Test
    void findStatusById() {
        Status status = mapper.findStatusById(3);
        assertEquals(Status.AVAIlABLE, status);
    }

    @Test
    void deleteById() {
        mapper.deleteById(1);

        List<Book> books = mapper.findAllBooks();
        Book firstBook = books.get(0);

        assertEquals(2, books.size());
        assertEquals(2, firstBook.id);
    }

    @Test
    void borrowBook() {
        var beforeBook = mapper.findById(1);
        assertEquals(Status.AVAIlABLE, beforeBook.status);

        mapper.borrowBook(1);

        var afterBook = mapper.findById(1);
        assertEquals(Status.BORROWED, afterBook.status);
    }

    @Test
    void returnBook() {
        mapper.borrowBook(2);
        var beforeBook = mapper.findById(2);
        assertEquals(Status.BORROWED, beforeBook.status);

        mapper.returnBook(2);
        var afterBook = mapper.findById(2);
        assertEquals(Status.AVAIlABLE, afterBook.status);
    }
}