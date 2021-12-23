package com.example.websocket.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.websocket.config.Const;
import com.example.websocket.entity.User;
import com.example.websocket.exception.RRException;
import com.example.websocket.mapper.UserMapper;
import com.example.websocket.service.UserService;
import com.example.websocket.utill.JwtUtill;
import com.example.websocket.utill.RedisUtils;
import com.example.websocket.vo.response.R;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    RedisUtils redisUtils;
    @Override
    public String check(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User loginUser = this.baseMapper.selectOne(queryWrapper.eq(User::getPhoneNumber, user.getPhoneNumber()));
        Optional.ofNullable(loginUser).orElseThrow(()->new RRException("用户不存在！！",400));
        String decPassWord = SecureUtil.md5(user.getPassword() + loginUser.getSalt());
        if(!loginUser.getPassword().equals(decPassWord)){
            throw new RRException("账号或密码错误！！",400);
        }
        //jwt加密用户信息
        String token = JwtUtill.genToken(loginUser);
        //在线用户存入redies
        redisUtils.set(user.getPhoneNumber(),token);
        return token;
    }
}