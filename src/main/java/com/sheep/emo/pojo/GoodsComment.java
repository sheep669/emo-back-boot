package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (GoodsComment)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class GoodsComment implements Serializable {
    private static final long serialVersionUID = -48033579223697267L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 买家信息
     */
    private String buyerInformation;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品规格
     */
    private String goodsSpecification;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论图片
     */
    private String commentImage;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    private String auditStatus;
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

