package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (SystemLoginLog)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@Getter
@Setter
@ToString
public class SystemLoginLog implements Serializable {
    private static final long serialVersionUID = 218979920484203299L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 登录时间
     */
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}

