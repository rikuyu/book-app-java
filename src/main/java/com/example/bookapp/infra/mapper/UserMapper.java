package com.example.bookapp.infra.mapper;

import com.example.bookapp.domain.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> findAllUsers();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(int id);

    @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET email = #{email} WHERE id = #{id}")
    void updateEmail(User user);

    @Update("UPDATE users SET name = #{name} WHERE id = #{id}")
    void updateName(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(int id);
}
