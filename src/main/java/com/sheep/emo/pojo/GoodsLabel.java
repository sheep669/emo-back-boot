package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (GoodsLabel)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class GoodsLabel implements Serializable {
    private static final long serialVersionUID = -75682567951723733L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 标签名称
     */
    private String labelName;
    /**
     * 标签编码
     */
    private Integer commodityCode;
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

