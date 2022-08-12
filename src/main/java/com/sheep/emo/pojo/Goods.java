package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表(Goods)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class Goods implements Serializable {
    private static final long serialVersionUID = -29204826517507680L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品分类
     */
    private String goodsCategory;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 总库存
     */
    private Long totalStocks;
    /**
     * 显示销量
     */
    private Long showSales;
    /**
     * 真实销量
     */
    private Long realSales;
    /**
     * 序号
     */
    private Long serialNumber;
    /**
     * 商品状态 1:出库中 2:仓库中 0:已售罄
     */
    private String goodsStatus;
    /**
     * 产生时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date makeTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

