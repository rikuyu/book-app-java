package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.Book;
import com.example.bookapp.domain.entity.BorrowRecord;
import com.example.bookapp.domain.entity.Status;
import com.example.bookapp.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@TestPropertySource(locations = "classpath:application-test.properties")
class BorrowRecordMapperTest {

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void findAllBorrowRecords() {
        var borrowRecords = borrowRecordMapper.findAllBorrowRecords();
        var firstBorrowRecord = borrowRecords.get(0);

        assertEquals(borrowRecords.size(), 3);
        assertEquals(firstBorrowRecord.id, 1);
        assertEquals(firstBorrowRecord.userId, 1);
        assertEquals(firstBorrowRecord.bookId, 1);
        assertEquals(firstBorrowRecord.returnedDate, null);
    }

    @Test
    void findByUserId() {
        var borrowRecords = borrowRecordMapper.findByUserId(1);
        var firstBorrowRecord = borrowRecords.get(0);

        assertEquals(borrowRecords.size(), 1);
        assertEquals(firstBorrowRecord.id, 1);
        assertEquals(firstBorrowRecord.userId, 1);
        assertEquals(firstBorrowRecord.bookId, 1);
        assertEquals(firstBorrowRecord.returnedDate, null);
    }

    @Test
    void findByBookId() {
        var borrowRecords = borrowRecordMapper.findByBookId(1);
        var firstBorrowRecord = borrowRecords.get(0);

        assertEquals(borrowRecords.size(), 1);
        assertEquals(firstBorrowRecord.id, 1);
        assertEquals(firstBorrowRecord.userId, 1);
        assertEquals(firstBorrowRecord.bookId, 1);
        assertEquals(firstBorrowRecord.returnedDate, null);
    }

    @Test
    void insertBorrowRecord() {
        var userId = 4;
        var bookId = 4;
        var newBook = new Book(bookId, "Test Book", new Date(), Status.AVAIlABLE);
        var newUser = new User(userId, "Takashi", "takashi@gmail.com");

        bookMapper.insertBook(newBook);
        userMapper.insert(newUser);

        var borrowRecord = new BorrowRecord(4, userId, bookId, new Date(), null);
        borrowRecordMapper.insertBorrowRecord(borrowRecord);

        var borrowRecords = borrowRecordMapper.findAllBorrowRecords();
        var lastBorrowRecord = borrowRecords.get(3);

        assertEquals(borrowRecords.size(), 4);
        assertEquals(lastBorrowRecord.id, 4);
        assertEquals(lastBorrowRecord.userId, userId);
        assertEquals(lastBorrowRecord.bookId, bookId);
        assertEquals(lastBorrowRecord.returnedDate, null);
    }

    @Test
    void updateBorrowRecord() {
        var id = 1;
        var beforeBorrowRecord = borrowRecordMapper.findByBookId(id).get(0);
        assertEquals(beforeBorrowRecord.returnedDate, null);

        borrowRecordMapper.updateBorrowRecord(id);
        var afterBorrowRecord = borrowRecordMapper.findByBookId(id).get(0);
        assertNotNull(afterBorrowRecord.returnedDate);
    }

    @Test
    void deleteBorrowRecordByBookId() {
        borrowRecordMapper.deleteBorrowRecordByBookId(1);
        var borrowRecords = borrowRecordMapper.findAllBorrowRecords();
        assertEquals(borrowRecords.size(), 2);
    }

    @Test
    void deleteBorrowRecordByUserId() {
        borrowRecordMapper.deleteBorrowRecordByUserId(1);
        var borrowRecords = borrowRecordMapper.findAllBorrowRecords();
        assertEquals(borrowRecords.size(), 2);
    }
}