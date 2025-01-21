package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getBook() {
        return service.findAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping()
    public void addBook(@RequestBody Book book) {
        service.insertBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        service.deleteById(id);
    }
}
