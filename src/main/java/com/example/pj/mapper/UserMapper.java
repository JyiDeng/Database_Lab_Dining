package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE UserID = #{userId}")
    User findByID(Long id);

    @Select("SELECT * FROM user WHERE UserName = #{userName}")
    User findByUsername(String userName);

    @Insert("INSERT INTO user (UserId,UserName,Gender,EcardId,Role,Age,Password) VALUES (#{userId},#{userName},#{gender},#{ecardId},#{role},#{age},#{password})")
    void insert(User user);
    // 为了让数据库真的加进去这个人，既可以传user又可以传参数吗，可以的 TODO

    @Update("UPDATE user SET UserName = #{userName}, Gender = #{gender}, EcardID = #{ecardId}, Role = #{role}, Age = #{age}, Password = #{password} WHERE UserID = #{userId}")
    void update(User user);

    @Delete("DELETE FROM user WHERE UserID = #{userId}")
    void delete(Long id);

    int save(@Param("user") User user);


}
