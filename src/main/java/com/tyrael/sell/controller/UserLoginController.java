package com.tyrael.sell.controller;


import com.tyrael.sell.dataobject.SellerInfo;
import com.tyrael.sell.enums.ResultEnum;
import com.tyrael.sell.exception.SellException;
import com.tyrael.sell.form.SellerForm;
import com.tyrael.sell.service.SellerService;
import com.tyrael.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/login")
@Slf4j
public class UserLoginController {


    @Autowired
    private SellerService sellerService;

    @GetMapping("/registerInfo")
    public ModelAndView register(@Valid SellerForm form, BindingResult bindingResult, Map<String, Object> map){

        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/login/register");
            return new ModelAndView("common/error", map);
        }
        SellerInfo sellerInfo = new SellerInfo();

        try {
//            //如果openid为空，说明是新增
//            if (!StringUtils.isEmpty(form.getOpenid())){
//                sellerInfo = sellerService.findSellerInfoByOpenid(form.getOpenid());
//            }else{
//                form.setSellerId(KeyUtil.genUniqueKey());
//            }

            if(sellerService.findSellerInfoByOpenid(form.getOpenid()) != null){
                map.put("msg",ResultEnum.OPNEID_REPEAT.getMessage());
                map.put("url", "/sell/login/register");
                return new ModelAndView("common/error", map);
            }

            form.setSellerId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(form, sellerInfo);
            sellerService.save(sellerInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/login/register");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/login/index");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("login/index");
    }

    @GetMapping("/register")
    public ModelAndView registerPage(){
        return new ModelAndView("login/register");
    }
}
