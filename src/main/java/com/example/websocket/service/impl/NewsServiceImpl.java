package com.example.websocket.service.impl;

import com.example.websocket.entity.News;
import com.example.websocket.mapper.NewsMapper;
import com.example.websocket.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxs
 * @since 2021-12-23
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

}
