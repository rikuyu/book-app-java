package com.example.bookapp.domain.entity;

public class Book {
    public Integer id;
    public String title;
    public Status status = Status.AVAIlABLE;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(Integer id, String title, Status status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }
}

