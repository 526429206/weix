package com.example.websocket.Interceptor;

import com.example.websocket.entity.User;
import com.example.websocket.exception.RRException;
import com.example.websocket.utill.JwtUtill;
import com.example.websocket.utill.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtils redisUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        Optional.ofNullable(authorization).orElseThrow(()->new RRException("系统未检测到token信息！！", 400) );
        //判断请求头信息是否为空，或者是否已Bearer开头
        if (authorization.startsWith("Bearer")) {
            //获取token数据
            String token = authorization.replace("Bearer ", "");
            User user = JwtUtill.pareToken(token);
           if( redisUtils.hasKey(user.getPhoneNumber())){
               return true;
           }
            throw new RRException("用户信息已过期，请重新登入！！", 401);
        }
        throw new RRException("token信息有误，请重新确认！！", 400);
    }
}