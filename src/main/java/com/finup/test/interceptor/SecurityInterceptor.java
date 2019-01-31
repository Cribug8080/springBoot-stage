package com.finup.test.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SecurityInterceptor implements HandlerInterceptor {

    @Value("${url.token}")
    private String urlToken;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
        String token = request.getHeader("token");

        if(token.equals(urlToken)){//系统通用token
            return true;
        }

        Cache cache = cacheManager.getCache("LOGINUSER");

        if(cache != null && cache.get(token) != null){
            String username = cache.get(token).toString();
            return true;
        }else{
            errorResponse(response);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void errorResponse(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            JSONObject res = new JSONObject();
            res.put("status","-99");
            res.put("message","登录失败，请重新登录");
            out.append(res.toString());
        }catch (Exception e){
            response.sendError(500);
        }


    }
}
