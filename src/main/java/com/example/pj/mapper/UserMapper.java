package com.example.pj.mapper;

import com.example.pj.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE UserID = #{id}")
    User findByID(Long id);

    @Insert("INSERT INTO user (UserID,UsrName,Gender,EcardID,Role,Age) VALUES (#{userID},#{usrName},#{gender},#{ecardID},#{role},#{age})")
    void insert(User user);

    @Update("UPDATE user SET Usrname = #{name}, Gender = #{gender}, EcardID = #{ecardID}, Role = #{role}, Age = #{age} WHERE UserID = #{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE UserID = #{userID}")
    void delete(Long id);

    int save(@Param("user") User user);
}
