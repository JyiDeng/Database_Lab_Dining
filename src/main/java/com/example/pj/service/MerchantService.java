package com.example.pj.service;

import com.example.pj.entity.Merchant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {



    public List<Merchant> searchMerchant(String keyword);


}
