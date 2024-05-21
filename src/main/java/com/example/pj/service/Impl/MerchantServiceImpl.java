package com.example.pj.service.Impl;

import com.example.pj.dao.MerchantMapper;
import com.example.pj.dao.UserMapper;
import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import com.example.pj.service.MerchantService;
import com.example.pj.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantMapper merchantMapper;

    public List<Merchant> searchMerchant(String keyword){
        return merchantMapper.searchMerchant(keyword);
    }

}
