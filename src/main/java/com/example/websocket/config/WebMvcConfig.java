package com.example.websocket.config;

import com.example.websocket.Interceptor.LoginInterceptor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor interceptor;

    /**
     * 添加拦截器的配置
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //1.添加自定义拦截器
        registry.addInterceptor(interceptor).
                //2.指定拦截器的url地址
                addPathPatterns("/**").
                //3.指定不拦截的url地址
                excludePathPatterns("/login","/api/ws/sendOne","/test");
    }
}