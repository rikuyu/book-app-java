package com.example.backend.domain.service;

import com.example.backend.domain.entity.BorrowRecord;
import com.example.backend.domain.entity.Status;
import com.example.backend.infra.mapper.BookMapper;
import com.example.backend.infra.mapper.BorrowRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowRecordService {

    private final BorrowRecordMapper borrowRecordMapper;
    private final BookMapper bookMapper;

    @Autowired
    public BorrowRecordService(
            BorrowRecordMapper borrowRecordMapper,
            BookMapper bookMapper
    ) {
        this.borrowRecordMapper = borrowRecordMapper;
        this.bookMapper = bookMapper;
    }

    public List<BorrowRecord> findAllBorrowRecords() {
        return borrowRecordMapper.findAllBorrowRecords();
    }

    public List<BorrowRecord> findByUserId(int userId) {
        return borrowRecordMapper.findByUserId(userId);
    }

    public List<BorrowRecord> findByBookId(int bookId) {
        return borrowRecordMapper.findByBookId(bookId);
    }

    @Transactional
    public boolean insertBorrowRecordIfAvailable(BorrowRecord borrowRecord) {
        boolean isBookAvailable = bookMapper.findStatusById(borrowRecord.bookId) == Status.AVAIlABLE;

        if (!isBookAvailable) {
            return false;
        }

        bookMapper.borrowBook(borrowRecord.bookId);
        borrowRecord.setBorrowedDate();
        borrowRecordMapper.insertBorrowRecord(borrowRecord);
        return true;
    }

    @Transactional
    public boolean returnBook(int borrowRecordId, int bookId) {
        int affectedRows = borrowRecordMapper.updateBorrowRecord(borrowRecordId, bookId);
        if (affectedRows == 0) {
            return false;
        }
        bookMapper.returnBook(bookId);
        return true;
    }
}
