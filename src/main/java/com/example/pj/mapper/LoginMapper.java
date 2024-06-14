package com.example.pj.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT COUNT(*) FROM admin WHERE name = #{username} AND password = #{password}")
    boolean validateAdmin(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM user WHERE username = #{username} AND password = #{password}")
    boolean validateUser(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM merchant WHERE username = #{username} AND password = #{password}")
    boolean validateMerchant(@Param("username") String username, @Param("password") String password);
}

