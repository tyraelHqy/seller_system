package com.tyrael.sell.service;

import com.tyrael.sell.dataobject.SellerInfo;



public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
    SellerInfo save(SellerInfo sellerInfo);
}
