package com.sheep.emo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2022-07-29 09:52:01
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 734502318266104527L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 角色
     */
    private String role;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 账户余额
     */
    private BigDecimal accountBalance;
    /**
     * 所在地区
     */
    private String location;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 推荐人
     */
    private String referrer;
    /**
     * 状态 1:启用 2:禁用 默认值:1
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}

