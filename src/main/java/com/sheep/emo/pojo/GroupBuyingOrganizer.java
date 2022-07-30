package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (GroupBuyingOrganizer)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:32:20
 */
@Getter
@Setter
@ToString
public class GroupBuyingOrganizer implements Serializable {
    private static final long serialVersionUID = -97810184898686414L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 联系方式
     */
    private String phoneNumber;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 推荐团长
     */
    private String recommendGroupBuyingOrganizer;
    /**
     * 提货地址
     */
    private String receiverAddress;
    /**
     * 佣金余额
     */
    private BigDecimal commissionBalance;
    /**
     * 收益总额
     */
    private BigDecimal earningsBalance;
    /**
     * 现金余额
     */
    private BigDecimal cashBalance;
    /**
     * 团长状态 1:启用 2:禁用
     */
    private String groupBuyingOrganizerStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 推荐人
     */
    private String referrer;
    /**
     * 申请时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    private String auditStatus;
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

