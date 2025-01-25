package com.example.bookapp.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BorrowRecord {
    public Integer id;
    public Integer userId;
    public Integer bookId;
    public Date borrowedDate;
    public Date returnedDate;

    @JsonCreator
    public BorrowRecord(
            @JsonProperty("user_id") Integer userId,
            @JsonProperty("book_id") Integer bookId
    ) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public void setBorrowedDate() {
        this.borrowedDate = new Date();
    }
}
