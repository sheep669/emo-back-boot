package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (CompeteOrder)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:30
 */
@Getter
@Setter
@ToString
public class CompeteOrder implements Serializable {
    private static final long serialVersionUID = -71142350391441250L;
    /**
     * 主键id
     */
    private Long id;

    /**
     * 提货码
     */
    private Integer takeGoodsCode;
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
     * 订单备注
     */
    private String orderRemarks;
    /**
     * 下单时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date purchasingTime;
    /**
     * 提货时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date takeGoodsTime;
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

