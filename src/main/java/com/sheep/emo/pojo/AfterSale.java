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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 售后表(AfterSale)实体类
 *
 * @author makejava
 * @since 2022-08-22 20:49:23
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
@ApiModel(value = "AfterSale对象", description = "售后表")
public class AfterSale implements Serializable {
    private static final long serialVersionUID = -36329215745547294L;

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderNo;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Long orderId;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long goodsId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String storeName;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    /**
     * 买家姓名
     */
    @ApiModelProperty(value = "买家姓名")
    private String buyerName;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    @ApiModelProperty(value = "审核状态<br> 1:待审核  2:审核中 3:通过 4:不予通过")
    private String auditStatus;
    /**
     * 业务类型（0退款, 1退货）
     */
    @ApiModelProperty(value = "业务类型（0退款, 1退货）")
    private String orderAfterSaleType;
    /**
     * 退款类型（0原路退回, 1退至钱包, 2手动处理）
     */
    @ApiModelProperty(value = "退款类型<br>(0原路退回, 1退至钱包, 2手动处理)")
    private String refundType;
    /**
     * 申请原因
     */
    @ApiModelProperty(value = "申请原因")
    private String reason;
    /**
     * 退货数量
     */
    @ApiModelProperty(value = "退货数量")
    private Long orderAfterSaleNumber;
    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    private BigDecimal price;
    /**
     * 退款说明
     */
    @ApiModelProperty(value = "退款说明")
    private String refundInstruction;
    /**
     * 凭证图片
     */
    @ApiModelProperty(value = "凭证图片")
    private String images;
    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    /**
     * 退货时间
     */
    @ApiModelProperty(value = "退货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;
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

