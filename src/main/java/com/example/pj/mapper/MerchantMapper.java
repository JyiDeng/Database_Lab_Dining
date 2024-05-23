package com.example.pj.mapper;

import com.example.pj.entity.Dish;
import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {


    @Select("SELECT * FROM merchant")
    List<User> findAll();
    @Select("SELECT * FROM merchant WHERE MerchantName LIKE CONCAT('%', #{keyword}, '%') OR Address LIKE CONCAT('%', #{keyword}, '%')")
//    List<Merchant> searchMerchant(@Param("keyword") String keyword);
    List<Merchant> searchMerchant(@Param("keyword") String keyword);
    Merchant getMerchantById(@Param("id") Long id);
    List<Dish> getDishesByMerchantId(@Param("merchantId") Long merchantId);
}
