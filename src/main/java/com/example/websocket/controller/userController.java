package com.example.websocket.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.websocket.entity.User;
import com.example.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("user")
public class userController {
    @Autowired
    UserService userService;
    @RequestMapping("list")
    public List<User> list(){
        QueryWrapper<User> wap=new QueryWrapper();
        LambdaQueryWrapper<User> qla = wap.lambda();
        return userService.list(qla.eq(User::getEmail,"user1@xkcoding.com"));
    }

    /**
     * 保存用户
     * @return
     */
    @PostMapping("save")
    public User saveUser(@RequestBody User user){
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User.builder()
                .name(user.getName())
                .password(SecureUtil.md5(user.getPassword() + salt))
                .salt(salt).email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(1)
                .lastLoginTime(new DateTime())
                .build();
        boolean save = userService.save(testSave3);
        return testSave3;
//测试数据
       /* {
            "name": "user_3",
                "password": "zxs12345",
                "email": "1063625067.com",
                "phoneNumber": "15880308625"
        }*/
    }
}
