package com.finup.test.controller;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello(String name){
        if(name.equalsIgnoreCase("wl")){
            return "hello spring boot";
        }else{
            return 1/0 + "";
        }
    }

    @GetMapping("/test/getTestData")
    @ApiOperation("测试获取数据")
    public String getTestData(String name){
        return name + "getTestData";
    }
}
