package com.finup.test.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Message {

    @GetMapping("/message/count")
    @ApiOperation("获取消息数量")
    public Integer count(){
        return 10;
    }
}
