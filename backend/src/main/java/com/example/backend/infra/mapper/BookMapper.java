package com.example.backend.infra.mapper;

import com.example.backend.domain.entity.Book;
import com.example.backend.domain.entity.Status;
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

    List<Book> search(String keyword);

    List<Book> getPopularBooks();
}
