package com.example.bookapp.domain.entity;

import java.util.Date;

public class Book {
    public Integer id;
    public String title;
    public Date publishedDate;
    public Status status;

    public Book(Integer id, String title, Date publishedDate, Status status) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.status = status;
    }
}

