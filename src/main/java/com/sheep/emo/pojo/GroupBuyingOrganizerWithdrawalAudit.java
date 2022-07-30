package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (GroupBuyingOrganizerWithdrawalAudit)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@Getter
@Setter
@ToString
public class GroupBuyingOrganizerWithdrawalAudit implements Serializable {
    private static final long serialVersionUID = -29278611165417764L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 团长名称
     */
    private String groupBuyingOrganizerName;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 电话
     */
    private Integer phoneNumber;
    /**
     * 到账类型
     */
    private String arrivalAmountType;
    /**
     * 账号信息
     */
    private String accountInformation;
    /**
     * 提现金额(元)
     */
    private BigDecimal drawnMoney;
    /**
     * 申请日期
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

