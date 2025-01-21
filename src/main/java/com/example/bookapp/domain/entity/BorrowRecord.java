package com.example.bookapp.domain.entity;

import java.util.Date;

public class BorrowRecord {
    public Integer id;
    public Integer userId;
    public Integer bookId;
    public Date borrowedDate;
    public Date returnedDate;

    public BorrowRecord(
            Integer id,
            Integer userId,
            Integer bookId,
            Date borrowedDate,
            Date returnedDate
    ) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }
}
