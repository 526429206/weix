package com.example.websocket.controller;

import com.example.websocket.entity.User;
import com.example.websocket.service.UserService;
import com.example.websocket.utill.RedisUtils;
import com.example.websocket.vo.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登入
 */
@RestController
public class loginController {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtils redisUtils;
    @PostMapping("login")
    public R login(@RequestBody User user){
        userService.check(user);
        return R.ok();
    }
    //退出登入
    @PostMapping("loginOut")
    public R loginOut(@RequestBody User user){
        redisUtils.del(user.getPhoneNumber());
        return R.ok();
    }
}
