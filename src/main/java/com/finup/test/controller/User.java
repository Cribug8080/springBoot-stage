package com.finup.test.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class User {

    @GetMapping("/getUserInfo")
    @ApiOperation("获取登录人信息")
    public String getUserInfo(){
        String res = "";
        JSONObject json = new JSONObject();
        json.put("user_id","1");

        String[] array = {"super_admin","admin"};
        json.put("access", array);
        json.put("token","super_admin");
        json.put("avator","https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
        return json.toString();
    }

    /**
     * 测试分页数据
     * @param reqMap
     * @return
     */
    @PostMapping("/getUsersWithPage")
    @ApiOperation("分页获取所有人员")
    public JSONObject getUsersWithPage(@RequestBody Map reqMap){
        JSONObject res = new JSONObject();

        int page = Integer.parseInt(reqMap.getOrDefault("page",1).toString());
        int pageSize = Integer.parseInt(reqMap.getOrDefault("pageSize",10).toString());
        String name = reqMap.getOrDefault("name","name").toString();
        String sex = reqMap.getOrDefault("sex","").toString();

        List<JSONObject> list = new ArrayList();
        int total = 45;
        int size = page*pageSize > total ? (total-(page-1)*pageSize) : pageSize;
        for(int i=0;i<size;i++){
            JSONObject temp = new JSONObject();
            temp.put("name",name+Math.round(Math.random()*50));
            temp.put("age",Math.round(Math.random()*100));
            temp.put("sex", sex.isEmpty() ? (Math.random() < 0.5 ? "M" : "W") : sex);
            list.add(temp);
        }

        res.put("list",list);
        res.put("total",total);
        res.put("page",page);
        res.put("pageSize",pageSize);
        return res;
    }

    /**
     * 测试数据
     * @param name[]
     * return list
     */

    @GetMapping("/getUserByName")
    @ApiOperation("根据名字查询人")
    public List getUserByName(@RequestParam() String[] names){
        List<JSONObject> list = new ArrayList<>();
        for(int i = 0;i<names.length;i++){
            JSONObject json = new JSONObject();
            json.put("name",names[i]);
            json.put("sex","M");
            list.add(json);
        }
        return list;
    }
}
