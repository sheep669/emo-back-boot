package com.sheep.emo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (GoodsCategory)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class GoodsCategory implements Serializable {
    private static final long serialVersionUID = 276782930354987014L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 商品数量
     */
    private Integer goodsNumber;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 添加时间
     */
    private Date addTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}

