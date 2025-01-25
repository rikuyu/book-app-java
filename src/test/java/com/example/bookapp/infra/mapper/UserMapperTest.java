package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.User;
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
        assertEquals(1, firstUser.id);
        assertEquals("Sakura", firstUser.name);
    }

    @Test
    void findById() {
        User user = mapper.findById(2);

        assertEquals(2, user.id);
        assertEquals("Kenta", user.name);
    }

    @Test
    void insert() {
        User newUser = new User("Takashi", "takashi@gmail.com");
        mapper.insert(newUser);

        List<User> users = mapper.findAllUsers();
        assertEquals(4, users.size());
        assertEquals(4, newUser.id);
    }

    @Test
    void deleteById() {
        mapper.deleteById(1);

        List<User> users = mapper.findAllUsers();
        var firstUser = users.get(0);

        assertEquals(2, users.size());
        assertEquals(2, firstUser.id);
    }
}
