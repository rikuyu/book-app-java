package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.BorrowRecord;
import com.example.bookapp.infra.mapper.BorrowRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordService {

    private final BorrowRecordMapper mapper;

    @Autowired
    public BorrowRecordService(BorrowRecordMapper mapper) {
        this.mapper = mapper;
    }

    public List<BorrowRecord> findAllBorrowRecords() {
        return mapper.findAllBorrowRecords();
    }

    public void insertBorrowRecord(BorrowRecord borrowRecord) {
        mapper.insertBorrowRecord(borrowRecord);
    }

    public List<BorrowRecord> findByUserId(int userId) {
        return mapper.findByUserId(userId);
    }

    public List<BorrowRecord> findByBookId(int bookId) {
        return mapper.findByBookId(bookId);
    }
}
