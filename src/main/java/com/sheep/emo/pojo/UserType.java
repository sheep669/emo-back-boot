package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类型表(UserType)实体类
 *
 * @author makejava
 * @since 2022-07-29 09:18:03
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class UserType implements Serializable {
    private static final long serialVersionUID = -46708324391184706L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 用户类型 1:普通会员 2:超级会员 3:商铺会员 4:团长 5:管理员
     */
    @ApiModelProperty(value = "用户类型<br>1:普通会员 2:超级会员 <br>3:商铺会员 4:团长 5:管理员")
    private String userType;
    /**
     * 会员数
     */
    @ApiModelProperty(value = "会员数")
    private Long userNumber;
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

