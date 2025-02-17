package com.example.backend.infra.mapper;

import com.example.backend.domain.entity.Book;
import com.example.backend.domain.entity.Status;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BookMapperTest {

    @Autowired
    private BookMapper mapper;

    @Test
    void findAllBooks_success() {
        List<Book> books = mapper.findAllBooks();
        Book firstBook = books.get(0);

        assertEquals(3, books.size());
        assertEquals(1, firstBook.id());
        assertEquals("Ruby on Rails Test Book", firstBook.title());
        assertEquals(Status.AVAILABLE, firstBook.status());
    }

    @Test
    void insertBook_success() {
        var title = "Test Book";
        Book newBook = new Book(1, title, Status.AVAILABLE);
        mapper.insertBook(newBook);

        List<Book> books = mapper.findAllBooks();
        var insertedBook = books.get(3);

        assertEquals(4, books.size());
        assertEquals(4, insertedBook.id());
        assertEquals(title, insertedBook.title());
    }

    @Test
    void findById_success() {
        Book book = mapper.findById(2);

        assertEquals(2, book.id());
        assertEquals("Golang Web Server Tutorial", book.title());
    }

    @Test
    void findStatusById_success() {
        Status status = mapper.findStatusById(3);
        assertEquals(Status.AVAILABLE, status);
    }

    @Test
    void deleteById_success() {
        mapper.deleteById(1);

        List<Book> books = mapper.findAllBooks();
        Book firstBook = books.get(0);

        assertEquals(2, books.size());
        assertEquals(2, firstBook.id());
    }

    @Test
    void borrowBook_success() {
        var beforeBook = mapper.findById(1);
        assertEquals(Status.AVAILABLE, beforeBook.status());

        mapper.borrowBook(1);

        var afterBook = mapper.findById(1);
        assertEquals(Status.BORROWED, afterBook.status());
    }

    @Test
    void returnBook_success() {
        mapper.borrowBook(2);
        var beforeBook = mapper.findById(2);
        assertEquals(Status.BORROWED, beforeBook.status());

        mapper.returnBook(2);
        var afterBook = mapper.findById(2);
        assertEquals(Status.AVAILABLE, afterBook.status());
    }

    @Test
    void search_success() {
        var keyword = "Java";
        var books = mapper.search(keyword);

        assertEquals(1, books.size());
        assertEquals(3, books.get(0).id());
        assertTrue(books.get(0).title().contains(keyword));
    }

    @Test
    void search_fail() {
        var keyword = "TypeScript";
        var books = mapper.search(keyword);

        assertEquals(0, books.size());
    }

    @Test
    void getPopularBooks_success() {
        var popularBooks = mapper.getPopularBooks();

        assertEquals(3, popularBooks.size());
    }
}