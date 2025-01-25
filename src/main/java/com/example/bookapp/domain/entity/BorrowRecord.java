package com.example.bookapp.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BorrowRecord {
    public Integer id;
    @JsonProperty("user_id")
    public Integer userId;
    @JsonProperty("book_id")
    public Integer bookId;
    @JsonProperty("borrowed_date")
    public Date borrowedDate;
    @JsonProperty("returned_date")
    public Date returnedDate;

    @JsonCreator
    public BorrowRecord(
            Integer userId,
            Integer bookId
    ) {
        this.userId = userId;
        this.bookId = bookId;
    }

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

    public void setBorrowedDate() {
        this.borrowedDate = new Date();
    }
}
