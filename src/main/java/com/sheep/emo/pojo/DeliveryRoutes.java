package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (DeliveryRoutes)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class DeliveryRoutes implements Serializable {
    private static final long serialVersionUID = -59920689200716324L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 路线名称
     */
    private String deliveryRoute;
    /**
     * 配送员
     */
    private String diliveryman;
    /**
     * 联系电话
     */
    private String phoneNumber;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}

