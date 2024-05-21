package com.example.pj.service;

import com.example.pj.dao.MerchantMapper;
import com.example.pj.entity.Merchant;
import com.example.pj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {



    public List<Merchant> searchMerchant(String keyword);


}
