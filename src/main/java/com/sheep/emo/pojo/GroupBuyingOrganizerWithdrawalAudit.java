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
 * 团长提现审核表(GroupBuyingOrganizerWithdrawalAudit)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class GroupBuyingOrganizerWithdrawalAudit implements Serializable {
    private static final long serialVersionUID = -29278611165417764L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 团长名称
     */
    @ApiModelProperty(value = "团长名称")
    private String groupBuyingOrganizerName;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String storeName;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @Pattern(regexp = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$", message = "手机号不合法")
    private String phoneNumber;
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
     * 提现金额(元)
     */
    @ApiModelProperty(value = "提现金额(元)")
    private BigDecimal drawnMoney;
    /**
     * 申请日期
     */
    @ApiModelProperty(value = "申请日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    @ApiModelProperty(value = "审核状态 1:待审核  2:审核中 3:通过 4:不予通过")
    private String auditStatus;
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

