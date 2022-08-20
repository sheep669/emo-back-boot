package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 团长表(GroupBuyingOrganizer)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:32:20
 */
@ApiModel(value = "GroupBuyingOrganizer对象", description = "团长信息")
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class GroupBuyingOrganizer implements Serializable {
    private static final long serialVersionUID = -97810184898686414L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "团长id")
    @TableId(type = IdType.AUTO)
    private Long id;
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
     * 推荐团长
     */
    @ApiModelProperty(value = "推荐团长")
    private String recommendGroupBuyingOrganizer;
    /**
     * 提货地址
     */
    @ApiModelProperty(value = "提货地址")
    private String receiverAddress;
    /**
     * 佣金余额
     */
    @ApiModelProperty(value = "佣金余额")
    private BigDecimal commissionBalance;
    /**
     * 收益总额
     */
    @ApiModelProperty(value = "收益总额")
    private BigDecimal earningsBalance;
    /**
     * 现金余额
     */
    @ApiModelProperty(value = "现金余额")
    private BigDecimal cashBalance;
    /**
     * 团长状态 1:启用 2:禁用
     */
    @ApiModelProperty(value = "团长状态<br> 1:启用 2:禁用")
    private String groupBuyingOrganizerStatus;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 推荐人
     */
    @ApiModelProperty(value = "推荐人")
    private String referrer;
    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    @ApiModelProperty(value = "审核状态<br> 1:待审核  2:审核中 3:通过 4:不予通过")
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

