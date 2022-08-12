package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品分类表(GoodsCategory)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class GoodsCategory implements Serializable {
    private static final long serialVersionUID = 276782930354987014L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 商品数量
     */
    private Long goodsNumber;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;
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

