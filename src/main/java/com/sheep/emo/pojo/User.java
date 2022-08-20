package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2022-07-29 09:52:01
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 734502318266104527L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private String role;
    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    @Pattern(regexp = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$", message = "手机号不合法")
    private String phoneNumber;
    /**
     * 账户余额
     */
    @ApiModelProperty(value = "账户余额")
    private BigDecimal accountBalance;
    /**
     * 居住地
     */
    @ApiModelProperty(value = "居住地")
    private String location;
    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String address;
    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registerTime;
    /**
     * 状态 1:启用 2:禁用 默认值:1
     */
    @ApiModelProperty(value = "状态 1:启用 2:禁用 默认值:1")
    private String status;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}

