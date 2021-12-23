package com.example.websocket.mapper;

import com.example.websocket.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxs
 * @since 2021-12-23
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
