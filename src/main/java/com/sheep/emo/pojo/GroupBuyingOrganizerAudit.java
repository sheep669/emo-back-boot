package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 团长审核表(GroupBuyingOrganizerAudit)实体类
 *
 * @author makejava
 * @since 2022-08-07 21:51:36
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Data
public class GroupBuyingOrganizerAudit implements Serializable {
    private static final long serialVersionUID = 588602206188667605L;
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
     * 团长姓名
     */
    private String groupBuyingOrganizerName;
    /**
     * 手机号
     */
    @Pattern(regexp = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$", message = "手机号不合法")
    private String phoneNumber;
    /**
     * 推荐人
     */
    private String referrer;
    /**
     * 审核状态 1:待审核 2:审核中 3:审核通过 0:审核不通过
     */
    private String auditStatus;
    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTime;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

