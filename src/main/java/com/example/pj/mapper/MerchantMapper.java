package com.example.pj.mapper;

import com.example.pj.entity.Merchant;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MerchantMapper {


    List<Merchant> searchMerchant(@Param("keyword") String keyword);
}
