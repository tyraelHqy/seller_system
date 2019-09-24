package com.tyrael.sell.service.impl;

import com.tyrael.sell.dataobject.SellerInfo;
import com.tyrael.sell.repository.SellerInfoRepository;
import com.tyrael.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return repository.save(sellerInfo);
    }
}
