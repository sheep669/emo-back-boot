package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志表(SystemLoginLog)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class SystemLoginLog implements Serializable {
    private static final long serialVersionUID = 218979920484203299L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;
    /**
     * 用户名
     */
    private String username;
    /**
     * 公司
     */
    private String companyName;
    /**
     * IP地址
     */
    private String ipAddress;
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

