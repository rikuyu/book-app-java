package com.example.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public class User {
    public Integer id;
    public String name;
    public String email;

    @JsonCreator
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
