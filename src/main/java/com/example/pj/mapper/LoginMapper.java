package com.example.pj.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT COUNT(*) FROM Admin WHERE adminId = #{adminId} AND password = #{password}")
    boolean validateAdmin(@Param("adminId") Long adminId, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM User WHERE userId = #{userId} AND password = #{password}")
    boolean validateUser(@Param("userId") Long userId, @Param("password") String password);

    @Select("SELECT COUNT(*) FROM Merchant WHERE merchantId = #{merchantId} AND password = #{password}")
    boolean validateMerchant(@Param("merchantId") Long merchantId, @Param("password") String password);
}

