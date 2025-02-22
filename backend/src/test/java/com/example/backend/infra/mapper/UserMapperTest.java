package com.example.backend.infra.mapper;

import com.example.backend.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void findAllUsers() {
        List<User> users = mapper.findAllUsers();
        var firstUser = users.get(0);

        assertEquals(3, users.size());
        assertEquals(1, firstUser.getId());
        assertEquals("Sakura", firstUser.getUsername());
    }

    @Test
    void findById() {
        User user = mapper.findById(2);

        assertEquals(2, user.getId());
        assertEquals("Kenta", user.getUsername());
    }

    @Test
    void insert() {
        User newUser = new User("Takashi", "takashi@gmail.com", "pw");
        newUser.setEncodedPassword(newUser.getPassword());
        mapper.insert(newUser);

        List<User> users = mapper.findAllUsers();
        assertEquals(4, users.size());
        assertEquals(4, newUser.getId());
    }

    @Test
    void deleteById() {
        mapper.deleteById(1);

        List<User> users = mapper.findAllUsers();
        var firstUser = users.get(0);

        assertEquals(2, users.size());
        assertEquals(2, firstUser.getId());
    }
}
