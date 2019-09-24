package com.tyrael.sell.form;

import lombok.Data;

import javax.persistence.Id;

@Data
public class SellerForm {

    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
