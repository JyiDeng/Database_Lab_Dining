package com.example.pj.mapper;

import com.example.pj.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE UserID = #{userId}")
    User findByID(Long id);

    @Insert("INSERT INTO user (UserId,UserName,Gender,EcardId,Role,Age) VALUES (#{userId},#{userName},#{gender},#{ecardId},#{role},#{age})")
    void insert(User user);

    @Update("UPDATE user SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardId}, Role = #{role}, Age = #{age} WHERE UserID = #{userId}")
    void update(User user);

    @Delete("DELETE FROM user WHERE UserID = #{userId}")
    void delete(Long id);

    int save(@Param("user") User user);
}
