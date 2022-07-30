package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (SystemOperateLog)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@Getter
@Setter
@ToString
public class SystemOperateLog implements Serializable {
    private static final long serialVersionUID = 804311889894246913L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 操作时间
     */
    private Date operateTime;
    /**
     * 操作日志
     */
    private String operateLog;
    /**
     * 模块
     */
    private String operateModule;
    /**
     * 操作人手机号
     */
    private String operatePhoneNumber;
    /**
     * 操作人权限
     */
    private String operatorAuthority;
    /**
     * 操作人姓名
     */
    private String operatorName;
    /**
     * 公司
     */
    private String companyName;
    /**
     * 操作结果 1:成功 0:失败
     */
    private String operateResult;
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

