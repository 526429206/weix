package com.example.websocket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxs
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 发送人姓名
     */
    private String sendUserName;

    /**
     * 接受人id
     */
    private String reciveUserId;

    /**
     * 接受人姓名
     */
    private String reciveUserName;

    /**
     * 发送消息
     */
    private String message;

    /**
     * 表情
     */
    private String emjo;

    /**
     * 图片
     */
    private String picture;


}
