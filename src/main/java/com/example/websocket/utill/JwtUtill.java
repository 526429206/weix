package com.example.websocket.utill;

import com.example.websocket.config.Const;
import com.example.websocket.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JwtUtill {
    //将user封装成token
    public static String genToken(User loginUser){
        JwtBuilder builder = Jwts.builder().setId(loginUser.getId())
                .setSubject(loginUser.getPhoneNumber())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, Const.JwtSecretKeys)
                //自定义claims存储数据
                .claim("userName", loginUser.getName())
                .claim("status", loginUser.getStatus());
        return "Bearer "+builder.compact();
    }
    /**
     * 解析token
     * @return 解析用户信息
     */
    public static User pareToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(Const.JwtSecretKeys).parseClaimsJws(token).getBody();
        User user = new User();
        user.setId(claims.getId());
        user.setPhoneNumber(claims.getSubject());
        user.setStatus((Integer) claims.get("status"));
        user.setName((String) claims.get("userName"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(user.getName()+"==>token签发时间:" + sdf.format(claims.getIssuedAt()));
        return user;


    }
}
