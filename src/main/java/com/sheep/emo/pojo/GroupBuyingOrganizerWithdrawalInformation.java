package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 团长提现信息表(GroupBuyingOrganizerWithdrawalInformation)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class GroupBuyingOrganizerWithdrawalInformation implements Serializable {
    private static final long serialVersionUID = -96355152110986968L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 团长姓名
     */
    @ApiModelProperty(value = "团长姓名")
    private String groupBuyingOrganizerName;
    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    @Pattern(regexp = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$", message = "手机号不合法")
    private String phoneNumber;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String storeName;
    /**
     * 店铺地址
     */
    @ApiModelProperty(value = "店铺地址")
    private String storeAddress;
    /**
     * 现金余额
     */
    @ApiModelProperty(value = "现金余额")
    private BigDecimal cashBalance;
    /**
     * 佣金余额
     */
    @ApiModelProperty(value = "佣金余额")
    private BigDecimal commissionBalance;
    /**
     * 已提现金额
     */
    @ApiModelProperty(value = "已提现金额")
    private BigDecimal drawnMoney;
    /**
     * 到账类型
     */
    @ApiModelProperty(value = "到账类型")
    private String arrivalAmountType;
    /**
     * 账号信息
     */
    @ApiModelProperty(value = "账号信息")
    private String accountInformation;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    @ApiModelProperty(value = "审核状态<br>1:待审核  2:审核中 <br>3:通过 4:不予通过")
    private String auditStatus;
    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

