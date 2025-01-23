package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.entity.Status;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> findAllBooks();

    void insertBook(Book book);

    Book findById(int id);

    Status findStatusById(int id);

    int deleteById(int id);

    void borrowBook(int id);

    void returnBook(int id);
}
