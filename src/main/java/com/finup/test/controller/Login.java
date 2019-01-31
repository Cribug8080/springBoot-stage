package com.finup.test.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Login {

    @Value("${url.token}")
    private String URLTOKEN;

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Map<String, String> login(@RequestBody Map<String,Object> reqMap , HttpServletRequest request){
        String userName = reqMap.get("userName").toString();
        String session = request.getSession(true).getId();

        Map<String, String> res = new HashMap<>();
        cacheManager.getCache("LOGINUSER").put(session,userName);
        res.put("token",session);
        res.put("userName", userName);

        return  res;
    }

    @PostMapping("/logout")
    @ApiOperation("登录")
    public Map<String, String> logout(HttpServletRequest request){
        String token = request.getHeader("Token");

        cacheManager.getCache("LOGINUSER").put(token, null);

        Map<String, String> res = new HashMap<>();
        res.put("msg","退出成功");
        return  res;
    }


}
