package com.example.websocket.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocket.entity.User;
import com.example.websocket.exception.RRException;
import com.example.websocket.mapper.UserMapper;
import com.example.websocket.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * User Service
 * </p>
 *
 * @author zxs
 * @date Created in 2021-12-08 18:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public void check(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User loginUser = this.baseMapper.selectOne(queryWrapper.eq(User::getPhoneNumber, user.getPhoneNumber()));
        Optional.ofNullable(loginUser).orElseThrow(()->new RRException("用户不存在！！",400));
        String decPassWord = SecureUtil.md5(user.getPassword() + loginUser.getSalt());
        if(!loginUser.getPassword().equals(decPassWord)){
            throw new RRException("账号或密码错误！！",400);
        }
    }
}