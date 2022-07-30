package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (Goods)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class Goods implements Serializable {
    private static final long serialVersionUID = -29204826517507680L;
    /**
     * 主键id
     */
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
    private Double goodsPrice;
    /**
     * 总库存
     */
    private Integer totalStocks;
    /**
     * 显示销量
     */
    private Integer showSales;
    /**
     * 真实销量
     */
    private Integer realSales;
    /**
     * 序号
     */
    private Integer serialNumber;
    /**
     * 商品状态 1:出库中 2:仓库中 0:已售罄
     */
    private String goodsStatus;
    /**
     * 产生时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date makeTime;
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

