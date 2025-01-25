package com.example.bookapp.domain.service;

import com.example.bookapp.domain.entity.BorrowRecord;
import com.example.bookapp.domain.entity.Status;
import com.example.bookapp.infra.mapper.BookMapper;
import com.example.bookapp.infra.mapper.BorrowRecordMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowRecordServiceTest {

    @InjectMocks
    BorrowRecordService service;

    @Mock
    private BorrowRecordMapper borrowRecordMapper;
    @Mock
    private BookMapper bookMapper;

    BorrowRecord mockBorrowRecord1 = new BorrowRecord(1, 1);
    BorrowRecord mockBorrowRecord2 = new BorrowRecord(2, 2);
    List<BorrowRecord> mockBorrowRecords = Arrays.asList(mockBorrowRecord1, mockBorrowRecord2);

    @Test
    void findAllBorrowRecords() {
        when(borrowRecordMapper.findAllBorrowRecords()).thenReturn(mockBorrowRecords);
        var borrowRecords = service.findAllBorrowRecords();

        assertEquals(mockBorrowRecords.size(), borrowRecords.size());
        assertEquals(mockBorrowRecords.get(0).userId, borrowRecords.get(0).userId);

        verify(borrowRecordMapper, times(1)).findAllBorrowRecords();
    }

    @Test
    void findByUserId() {
        when(borrowRecordMapper.findByUserId(1)).thenReturn(Collections.singletonList(mockBorrowRecord1));
        var borrowRecords = service.findByUserId(1);

        assertEquals(Collections.singletonList(mockBorrowRecord1).size(), borrowRecords.size());
        assertEquals(mockBorrowRecord1.userId, borrowRecords.get(0).userId);
        verify(borrowRecordMapper, times(1)).findByUserId(1);
    }

    @Test
    void findByBookId() {
        when(borrowRecordMapper.findByBookId(1)).thenReturn(Collections.singletonList(mockBorrowRecord1));
        var borrowRecords = service.findByBookId(1);

        assertEquals(Collections.singletonList(mockBorrowRecord1).size(), borrowRecords.size());
        assertEquals(mockBorrowRecord1.userId, borrowRecords.get(0).userId);
        verify(borrowRecordMapper, times(1)).findByBookId(1);
    }

    @Test
    void insertBorrowRecordIfAvailable() {
        when(bookMapper.findStatusById(1)).thenReturn(Status.AVAIlABLE);
        doNothing().when(bookMapper).borrowBook(mockBorrowRecord1.bookId);
        doNothing().when(borrowRecordMapper).insertBorrowRecord(mockBorrowRecord1);

        assertTrue(service.insertBorrowRecordIfAvailable(mockBorrowRecord1));

        verify(bookMapper).findStatusById(1);
        verify(bookMapper).borrowBook(mockBorrowRecord1.bookId);
        verify(borrowRecordMapper, times(1)).insertBorrowRecord(mockBorrowRecord1);
    }

    @Test
    void returnBook() {
        when(borrowRecordMapper.updateBorrowRecord(1, 1)).thenReturn(1);
        doNothing().when(bookMapper).returnBook(1);

        assertTrue(service.returnBook(1, 1));

        verify(bookMapper, times(1)).returnBook(1);
        verify(borrowRecordMapper, times(1)).updateBorrowRecord(1, 1);
    }
}