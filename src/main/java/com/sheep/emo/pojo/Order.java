package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (Order)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@Getter
@Setter
@ToString
public class Order implements Serializable {
    private static final long serialVersionUID = -22171314771446313L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 团长店铺
     */
    private String groupBuyingOrganizerStore;
    /**
     * 团长姓名
     */
    private String groupBuyingOrganizerName;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 收货信息
     */
    private String goodsReceivingInformation;
    /**
     * 订单状态 1:待付款 2:待备货 3:备货中4:配送中 5:待提货 6.已提货(交易完成) 0:已关闭
     */
    private String orderStatus;
    /**
     * 订单备注
     */
    private String orderRemarks;
    /**
     * 下单时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date purchasingTime;
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

