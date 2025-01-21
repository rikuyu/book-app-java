package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> findAllBooks();

    void insertBook(Book book);

    Book findById(int id);

    void deleteById(int id);

    void borrowBook(int id);

    void returnBook(int id);
}
