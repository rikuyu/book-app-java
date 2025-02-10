package com.example.backend.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public class User {
    public Integer id;
    public String name;
    public String email;
    public String password;
    public Role role = Role.USER;

    @JsonCreator
    public User(
            String name,
            String email,
            String password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(
            Integer id,
            String name,
            String email,
            String password,
            Role role
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
