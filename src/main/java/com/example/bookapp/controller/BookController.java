package com.example.bookapp.controller;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.service.BookService;
import com.example.bookapp.utils.BadRequestException;
import com.example.bookapp.utils.InternalServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Book>> getBook() {
        try {
            return ResponseEntity.ok(service.findAllBooks());
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        if (id <= 0) {
            throw new BadRequestException("id must be greater than 0");
        }

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @PostMapping()
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        try {
            service.insertBook(book);
            return ResponseEntity.ok("book inserted successfully");
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id) {
        if (id <= 0) {
            throw new BadRequestException("id must be greater than 0");
        }
        try {
            int affectedRows = service.deleteById(id);
            if (affectedRows == 0) {
                throw new InternalServerException("book is not exist or book is borrowed");
            }
            return ResponseEntity.ok("book deleted successfully");
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }
}
