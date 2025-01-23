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

        assertEquals(users.size(), 3);
        assertEquals(firstUser.id, 1);
        assertEquals(firstUser.name, "Sakura");

    }

    @Test
    void findById() {
        User user = mapper.findById(2);
        assertEquals(user.id, 2);
        assertEquals(user.name, "Kenta");
    }

    @Test
    void insert() {
        User newUser = new User(4, "Takashi", "takashi@gmail.com");
        mapper.insert(newUser);

        List<User> users = mapper.findAllUsers();
        assertEquals(users.size(), 4);
        assertEquals(newUser.id, 4);
    }

    @Test
    void updateName() {
        var beforeUser = mapper.findById(1);
        assertEquals(beforeUser.name, "Sakura");

        var newName = "Kana";
        mapper.updateName(new User(1, newName, "sakura@email.com"));
        var updatedUser = mapper.findById(1);
        assertEquals(updatedUser.name, newName);
    }

    @Test
    void updateEmail() {
        var beforeUser = mapper.findById(1);
        assertEquals(beforeUser.email, "sakura@email.com");

        var newEmail = "kana@email.com";
        mapper.updateEmail(new User(1, "Sakura", newEmail));
        var updatedUser = mapper.findById(1);
        assertEquals(updatedUser.email, newEmail);
    }

    @Test
    void deleteById() {
        mapper.deleteById(1);

        List<User> users = mapper.findAllUsers();
        var firstUser = users.get(0);

        assertEquals(users.size(), 2);
        assertEquals(firstUser.id, 2);
    }
}
