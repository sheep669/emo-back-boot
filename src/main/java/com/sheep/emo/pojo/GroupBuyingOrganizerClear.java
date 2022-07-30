package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (GroupBuyingOrganizerClear)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:30
 */
@Getter
@Setter
@ToString
public class GroupBuyingOrganizerClear implements Serializable {
    private static final long serialVersionUID = -22987323686431769L;
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
     * 已结算佣金
     */
    private BigDecimal balancedCommission;
    /**
     * 待结算佣金
     */
    private BigDecimal unbalancedCommission;
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

