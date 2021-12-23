package com.example.websocket.Interceptor;

import com.example.websocket.exception.RRException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        Optional.ofNullable(authorization).orElseThrow(()->new RRException("用户未登入", 401) );
        //判断请求头信息是否为空，或者是否已Bearer开头
        if (authorization.startsWith("Bearer")) {
            //获取token数据
            String token = authorization.replace("Bearer ", "");
            return true;
        }
        throw new RRException("用户未登入", 401);
    }
}