package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表(Member)实体类
 *
 * @author makejava
 * @since 2022-08-21 21:41:38
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
@ApiModel(value = "Member对象", description = "会员表")
public class Member implements Serializable {
    private static final long serialVersionUID = 694342108241037157L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 会员名字
     */
    @ApiModelProperty(value = "会员名字")
    private String username;
    /**
     * 会员密码
     */
    @ApiModelProperty(value = "会员密码")
    private String password;
    /**
     * 用户类型 1:vip 2:svip
     */
    @ApiModelProperty(value = "用户类型 1:vip 2:svip")
    private String memberType;
    /**
     * 会员状态 1：白名单 0：黑名单
     */
    @ApiModelProperty(value = "会员状态 1：白名单 0：黑名单")
    private String status;
    /**
     * 有效期 '2099-05-20 20:20:20' 为永不过期
     */
    @ApiModelProperty(value = "有效期 '2099-05-20 20:20:20' 为永不过期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

