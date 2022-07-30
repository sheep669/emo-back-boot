package com.sheep.emo.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (AfterSale)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class AfterSale implements Serializable {
    private static final long serialVersionUID = 956620433874986906L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 申请金额
     */
    private Double applyMoney;
    /**
     * 退货编号
     */
    private Integer returnGoodsNumber;
    /**
     * 买家姓名
     */
    private String buyerName;
    /**
     * 退款说明
     */
    private String refundInstruction;
    /**
     * 状态  1:已退货 2:已退款
     */
    private String status;
    /**
     * 申请时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
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

