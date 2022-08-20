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
 * 团长结算表(GroupBuyingOrganizerClear)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:30
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class GroupBuyingOrganizerClear implements Serializable {
    private static final long serialVersionUID = -22987323686431769L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
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
     * 已结算佣金
     */
    @ApiModelProperty(value = "已结算佣金")
    private BigDecimal balancedCommission;
    /**
     * 待结算佣金
     */
    @ApiModelProperty(value = "待结算佣金")
    private BigDecimal unbalancedCommission;
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

