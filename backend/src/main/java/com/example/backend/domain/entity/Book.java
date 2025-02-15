package com.example.backend.domain.entity;

public record Book(
        Integer id,
        String title,
        Status status
) {
    public Book {
        if (status == null) {
            status = Status.AVAILABLE;
        }
    }
}

