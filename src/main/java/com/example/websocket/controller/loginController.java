package com.example.websocket.controller;

import com.example.websocket.entity.User;
import com.example.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登入
 */
@RestController
public class loginController {
    @Autowired
    UserService userService;
    @RequestMapping("login")
    public void login(User user){
        userService.check(user);

    }
}
