package com.example.websocket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.websocket.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * UserMapper
 * </p>
 *
 * @author zxs
 * @date Created in 2021-12-08 18:10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}