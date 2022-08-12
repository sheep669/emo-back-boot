package com.sheep.emo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 售后表(AfterSale)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Data
public class AfterSale implements Serializable {
    private static final long serialVersionUID = 956620433874986906L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
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
    private BigDecimal applyMoney;
    /**
     * 退货编号
     */
    private Long returnGoodsNumber;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
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

