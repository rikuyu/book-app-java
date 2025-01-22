package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.infra.mapper.BookMapper;
import com.example.bookapp.infra.mapper.BorrowRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BorrowRecordMapper borrowRecordMapper;

    @Autowired
    public BookService(
            BookMapper bookMapper,
            BorrowRecordMapper borrowRecordMapper
    ) {
        this.bookMapper = bookMapper;
        this.borrowRecordMapper = borrowRecordMapper;
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
    public boolean deleteById(int id) {
        if (borrowRecordMapper.findByBookId(id).isEmpty()) {
            borrowRecordMapper.deleteBorrowRecordByBookId(id);
        }
        bookMapper.deleteById(id);
        return true;
    }
}
