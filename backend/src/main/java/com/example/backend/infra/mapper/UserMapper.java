package com.example.backend.infra.mapper;

import com.example.backend.domain.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> findAllUsers();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(int id);

    @Insert("INSERT INTO users (name, email, password) VALUES (#{name}, #{email}, #{encodedPassword})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteById(int id);
}
