package com.example.backend.domain.service;

import com.example.backend.domain.entity.Book;
import com.example.backend.infra.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public List<Book> findAllBooks() {
        return bookMapper.findAllBooks();
    }

    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }

    public Book findById(int id) {
        return bookMapper.findById(id);
    }

    @Transactional
    public int deleteById(int id) {
        return bookMapper.deleteById(id);
    }

    public List<Book> search(String keyword) {
        return bookMapper.search(keyword);
    }

    public List<Book> getPopularBooks() {
        return bookMapper.getPopularBooks();
    }
}
