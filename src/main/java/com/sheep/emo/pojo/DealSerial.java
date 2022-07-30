package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (DealSerial)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class DealSerial implements Serializable {
    private static final long serialVersionUID = -13528966992376924L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 支付流水号
     */
    private String paySerialId;
    /**
     * 订单总额
     */
    private BigDecimal totalOrderAmount;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 实收金额
     */
    private BigDecimal realAmount;
    /**
     * 团长店铺
     */
    private String storeName;
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

