package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.entity.Status;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;
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

        assertEquals(books.size(), 3);
        assertEquals(firstBook.id, 1);
        assertEquals(firstBook.title, "Ruby on Rails Test Book");
        assertEquals(firstBook.status, Status.AVAIlABLE);
    }

    @Test
    void insertBook() {
        Book newBook = new Book(4, "Test Book", new Date(), Status.AVAIlABLE);
        mapper.insertBook(newBook);

        List<Book> books = mapper.findAllBooks();
        var insertedBook = books.get(3);

        assertEquals(books.size(), 4);
        assertEquals(insertedBook.id, 4);
        assertEquals(insertedBook.title, "Test Book");
    }

    @Test
    void findById() {
        Book book = mapper.findById(2);

        assertEquals(book.id, 2);
        assertEquals(book.title, "Golang Web Server Tutorial");
    }

    @Test
    void findStatusById() {
        Status status = mapper.findStatusById(3);
        assertEquals(status, Status.AVAIlABLE);
    }

    @Test
    void deleteById() {
        mapper.deleteById(1);

        List<Book> books = mapper.findAllBooks();
        Book firstBook = books.get(0);

        assertEquals(books.size(), 2);
        assertEquals(firstBook.id, 2);
    }

    @Test
    void borrowBook() {
        var beforeBook = mapper.findById(1);
        assertEquals(beforeBook.status, Status.AVAIlABLE);

        mapper.borrowBook(1);

        var afterBook = mapper.findById(1);
        assertEquals(afterBook.status, Status.BORROWED);
    }

    @Test
    void returnBook() {
        mapper.borrowBook(2);
        var beforeBook = mapper.findById(2);
        assertEquals(beforeBook.status, Status.BORROWED);

        mapper.returnBook(2);
        var afterBook = mapper.findById(2);
        assertEquals(afterBook.status, Status.AVAIlABLE);
    }
}