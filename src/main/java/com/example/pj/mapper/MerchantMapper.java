package com.example.pj.mapper;

import com.example.pj.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {
//    @Select("SELECT * FROM Merchant")
//    List<User> findAll();

    @Select("SELECT MerchantId, MerchantName, MainDishes FROM Merchant WHERE MerchantName LIKE CONCAT('%', #{keyword}, '%') OR Address LIKE CONCAT('%', #{keyword}, '%')")
    List<Merchant> searchMerchant(@Param("keyword") String keyword);

    @Select("SELECT * FROM Merchant WHERE MerchantID = #{merchantId}")
    Merchant getMerchantByID(@Param("merchantId") Long merchantId);

    @Select("SELECT * FROM Merchant WHERE MerchantID = #{merchantId}")
    Merchant getMerchantDetails(@Param("merchantId") Long merchantId);

//    @Select("SELECT * FROM dish WHERE MerchantID = #{merchantId}")
//    List<Dish> getDishesByMerchantID(@Param("merchantId") Long merchantId);
    @Select("SELECT * FROM Merchant")
    List<Merchant> findAll();

    @Insert("INSERT INTO Merchant (merchantId,merchantName,mainDishes,address) VALUES (#{merchantId},#{merchantName},#{mainDishes},#{address})")
//    @Options(useGeneratedKeys = true, keyProperty = "merchantId")
    void insert(Merchant merchant);

    @Update("UPDATE Merchant SET merchantName = #{merchantName}, mainDishes = #{mainDishes}, address = #{address} WHERE merchantId = #{merchantId}")
    void update(Merchant merchant);

    @Delete("DELETE FROM Merchant WHERE merchantId = #{merchantId}")
    void delete(Long merchantId);
}
