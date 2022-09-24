package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表(Order)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class Orders implements Serializable {
    private static final long serialVersionUID = -22171314771446313L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 团长店铺
     */
    @ApiModelProperty(value = "团长店铺")
    private String groupBuyingOrganizerStore;
    /**
     * 团长姓名
     */
    @ApiModelProperty(value = "团长姓名")
    private String groupBuyingOrganizerName;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    /**
     * 购买者
     */
    @ApiModelProperty(value = "购买者")
    private String buyer;

    /**
     * 收货信息
     */
    @ApiModelProperty(value = "收货信息")
    private String goodsReceivingInformation;
    /**
     * 订单状态 1:待付款 2:待备货 3:备货中4:配送中 5:待提货 6.已提货(交易完成) 0:已关闭
     */
    @ApiModelProperty(value = "订单状态<br>1:待付款 2:待备货 <br>3:备货中4:配送中 5:待提货<br>6.已提货(交易完成) 0:已关闭")
    private String orderStatus;
    /**
     * 订单备注
     */
    @ApiModelProperty(value = "订单备注")
    private String orderRemarks;
    /**
     * 下单时间
     */
    @ApiModelProperty(value = "下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date purchasingTime;
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

