package com.example.websocket.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocket.entity.User;
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
    public boolean check(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User loginUser = this.baseMapper.selectOne(queryWrapper.eq(User::getPhoneNumber, user.getPhoneNumber()));
        if(Optional.ofNullable(loginUser).isPresent()){
            //Optional.ofNullable(对象).orElseThrow(()->new RuntimeException())；如果值不存在返回异常。

            String decPassWord = SecureUtil.md5(user.getPassword() + loginUser.getSalt());
            if(loginUser.getPassword().equals(decPassWord)){
                return true;
            }
        }

        return false;
    }
}