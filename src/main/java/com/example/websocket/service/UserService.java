package com.example.websocket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.websocket.entity.User;


/**
 * <p>
 * User Service
 * </p>
 *
 * @author zxs
 * @date Created in 2021-12-08 18:10
 */
public interface UserService extends IService<User> {
    String check(User user);
}