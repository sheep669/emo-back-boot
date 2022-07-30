package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (GroupBuyingOrganizerWithdrawalInformation)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@Getter
@Setter
@ToString
public class GroupBuyingOrganizerWithdrawalInformation implements Serializable {
    private static final long serialVersionUID = -96355152110986968L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 团长姓名
     */
    private String groupBuyingOrganizerName;
    /**
     * 联系方式
     */
    private String phoneNumber;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺地址
     */
    private String storeAdress;
    /**
     * 现金余额
     */
    private BigDecimal cashBalance;
    /**
     * 佣金余额
     */
    private BigDecimal commissionBalance;
    /**
     * 已提现金额
     */
    private Double drawnMoney;
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

