package com.example.pj.dao;

import com.example.pj.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    @Select("SELECT * FROM user")
    List<User> list();

    int save(@Param("user") User user);

}
