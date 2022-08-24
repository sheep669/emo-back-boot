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
import java.util.Date;

/**
 * 配送单管理表(DeliveryPaper)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:30
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class DeliveryPaper implements Serializable {
    private static final long serialVersionUID = 447601561744737672L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 店铺名称
     */
    @ApiModelProperty(value = "店铺名称")
    private String storeName;
    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String depotName;
    /**
     * 店铺地址
     */
    @ApiModelProperty(value = "店铺地址")
    private String storeAddress;
    /**
     * 配送路线
     */
    @ApiModelProperty(value = "配送路线")
    private String deliveryRoutes;
    /**
     * 配送单生成时间
     */
    @ApiModelProperty(value = "配送单生成时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date makeTime;
    /**
     * 配送状态 1:配送中 2:配送到达 3:已签收 0:配送关闭
     */
    @ApiModelProperty(value = "配送状态<br> 1:配送中 2:配送到达<br> 3:已签收 0:配送关闭")
    private String deliveryStatus;
    /**
     * 配送时间
     */
    @ApiModelProperty(value = "配送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;
    /**
     * 签收时间
     */
    @ApiModelProperty(value = "签收时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signTime;
    /**
     * 商品总数
     */
    @ApiModelProperty(value = "商品总数")
    private Long totalGoodsNumber;
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

