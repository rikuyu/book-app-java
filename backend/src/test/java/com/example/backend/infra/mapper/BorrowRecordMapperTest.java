package com.example.backend.infra.mapper;

import com.example.backend.domain.entity.Book;
import com.example.backend.domain.entity.BorrowRecord;
import com.example.backend.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        assertEquals(3, borrowRecords.size());
        assertEquals(1, firstBorrowRecord.id);
        assertEquals(1, firstBorrowRecord.userId);
        assertEquals(1, firstBorrowRecord.bookId);
        assertNull(firstBorrowRecord.returnedDate);
    }

    @Test
    void findByUserId() {
        var borrowRecords = borrowRecordMapper.findByUserId(1);
        var firstBorrowRecord = borrowRecords.get(0);

        assertEquals(1, borrowRecords.size());
        assertEquals(1, firstBorrowRecord.id);
        assertEquals(1, firstBorrowRecord.userId);
        assertEquals(1, firstBorrowRecord.bookId);
        assertNull(firstBorrowRecord.returnedDate);
    }

    @Test
    void findByBookId() {
        var borrowRecords = borrowRecordMapper.findByBookId(1);
        var firstBorrowRecord = borrowRecords.get(0);

        assertEquals(1, borrowRecords.size());
        assertEquals(1, firstBorrowRecord.id);
        assertEquals(1, firstBorrowRecord.userId);
        assertEquals(1, firstBorrowRecord.bookId);
        assertNull(firstBorrowRecord.returnedDate);
    }

    @Test
    void insertBorrowRecord() {
        var newBook = new Book("Test Book");
        var newUser = new User("Takashi", "takashi@gmail.com", "pw");

        bookMapper.insertBook(newBook);
        userMapper.insert(newUser);

        var borrowRecord = new BorrowRecord(4, 4);
        borrowRecord.setBorrowedDate();
        borrowRecordMapper.insertBorrowRecord(borrowRecord);

        var borrowRecords = borrowRecordMapper.findAllBorrowRecords();
        var lastBorrowRecord = borrowRecords.get(3);

        assertEquals(4, borrowRecords.size());
        assertEquals(4, lastBorrowRecord.id);
        assertEquals(4, lastBorrowRecord.userId);
        assertEquals(4, lastBorrowRecord.bookId);
        assertNull(lastBorrowRecord.returnedDate);
    }

    @Test
    void updateBorrowRecord() {
        var id = 1;
        var beforeBorrowRecord = borrowRecordMapper.findByBookId(id).get(0);
        assertNull(beforeBorrowRecord.returnedDate);

        int affectedRows = borrowRecordMapper.updateBorrowRecord(id, id);
        var afterBorrowRecord = borrowRecordMapper.findByBookId(id).get(0);

        assertEquals(1, affectedRows);
        assertNotNull(afterBorrowRecord.returnedDate);
    }
}