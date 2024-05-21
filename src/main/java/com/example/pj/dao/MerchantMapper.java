package com.example.pj.dao;

import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {


    List<Merchant> searchMerchant(@Param("keyword") String keyword);
}
