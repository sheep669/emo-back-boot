package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (CommissionSerial)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@Getter
@Setter
@ToString
public class CommissionSerial implements Serializable {
    private static final long serialVersionUID = 446334512438682800L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 团长名称
     */
    private String groupBuyingOrganizerName;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 佣金
     */
    private String commission;
    /**
     * 类型
     */
    private String type;
    /**
     * 产生时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date makeTime;
    /**
     * 备注
     */
    private String remark;
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

