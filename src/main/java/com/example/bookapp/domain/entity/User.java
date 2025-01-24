package com.example.bookapp.domain.entity;

public class User {
    public Integer id;
    public String name;
    public String email;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
