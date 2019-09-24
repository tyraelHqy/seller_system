package com.tyrael.sell.controller;


import com.tyrael.sell.constant.CookieConstant;
import com.tyrael.sell.constant.RedisConstant;
import com.tyrael.sell.dataobject.SellerInfo;
import com.tyrael.sell.enums.ResultEnum;
import com.tyrael.sell.service.SellerService;
import com.tyrael.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse response, Map<String,Object> map){

        //1. openid和数据库中的数据进行匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/login/index");
            return new ModelAndView("common/error",map);
        }

        //2. 设置token 至 redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid, expire,TimeUnit.SECONDS); //设置自动过期，如果不设置的话，信息会越存越多，占用资源。

        //3. 设置token 至 cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);


        return new ModelAndView("redirect:/seller/order/list");
    }


    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map){
        //1. 从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if(cookie!=null){
            //2. 清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/login/index");
        return new ModelAndView("common/success");
    }

}
