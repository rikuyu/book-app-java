package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.infra.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookMapper mapper;

    @Autowired
    public BookService(BookMapper mapper) {
        this.mapper = mapper;
    }

    public List<Book> findAllBooks(){
        return mapper.findAllBooks();
    }

    public void insertBook(Book book){
        mapper.insertBook(book);
    }

    public Book findById(int id){
        return mapper.findById(id);
    }

    public void deleteById(int id){
        mapper.deleteById(id);
    }

    public void borrowBook(int id){
        mapper.borrowBook(id);
    }

    public void returnBook(int id){
        mapper.returnBook(id);
    }
}
