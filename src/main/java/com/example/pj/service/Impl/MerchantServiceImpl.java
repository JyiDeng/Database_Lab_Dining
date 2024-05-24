package com.example.pj.service.Impl;

import com.example.pj.entity.Dish;
import com.example.pj.mapper.MerchantMapper;
import com.example.pj.entity.Merchant;
import com.example.pj.service.MerchantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantMapper merchantMapper;

    @Override
    public List<Merchant> searchMerchant(String keyword){
        return merchantMapper.searchMerchant(keyword);
    }


    @Override
    public Merchant getMerchantByID(Long id) {
        return merchantMapper.getMerchantByID(id);
    }

    @Override
    public List<Dish> getDishesByMerchantID(Long merchantId) {
        return merchantMapper.getDishesByMerchantID(merchantId);
    }

}
