package com.example.backend.controller;

import com.example.backend.domain.entity.Book;
import com.example.backend.domain.service.BookService;
import com.example.backend.utils.InternalServerException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
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
    public ResponseEntity<Book> getBookById(@PathVariable @Positive int id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> addBook(@RequestBody @NotNull Book book) {
        try {
            service.insertBook(book);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable @Positive() int id) {
        try {
            int affectedRows = service.deleteById(id);
            if (affectedRows == 0) {
                throw new InternalServerException("book is not exist or book is borrowed");
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam @NotBlank String keyword
    ) {
        try {
            return ResponseEntity.ok(service.search(keyword));
        } catch (Exception e) {
            throw new InternalServerException("something went wrong", e);
        }
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Book>> getPopularBooks() {
        try {
            return ResponseEntity.ok(service.getPopularBooks());
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
    }
}
