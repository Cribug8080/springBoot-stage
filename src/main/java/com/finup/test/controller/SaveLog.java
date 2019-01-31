package com.finup.test.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SaveLog {

    @GetMapping("/save_error_logger")
    @ApiOperation("保存错误日志")
    public Map<String,String> saveErrorLog(){
        Map<String,String> res = new HashMap<>();
        res.put("count","100");

        return res;
    }
}
