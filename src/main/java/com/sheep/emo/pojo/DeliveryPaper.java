package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (DeliveryPaper)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:30
 */
@Getter
@Setter
@ToString
public class DeliveryPaper implements Serializable {
    private static final long serialVersionUID = 447601561744737672L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 仓库名称
     */
    private String depotName;
    /**
     * 店铺地址
     */
    private String storeAddress;
    /**
     * 配送路线
     */
    private String deliveryRoutes;
    /**
     * 配送单生成时间
     */
    private Date makeTime;
    /**
     * 配送状态 1:配送中 2:配送到达 3:已签收 0:配送关闭
     */
    private String deliveryStatus;
    /**
     * 配送时间
     */
    private Date deliveryTime;
    /**
     * 签收时间
     */
    private Date signTime;
    /**
     * 商品总数
     */
    private Integer totalGoodsNumber;
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

