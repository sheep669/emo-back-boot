package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志表(SystemOperateLog)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:32
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class SystemOperateLog implements Serializable {
    private static final long serialVersionUID = 804311889894246913L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;
    /**
     * 操作日志
     */
    @ApiModelProperty(value = "操作日志")
    private String operateLog;
    /**
     * 模块
     */
    @ApiModelProperty(value = "模块")
    private String operateModule;
    /**
     * 操作人手机号
     */
    @ApiModelProperty(value = "操作人手机号")
    @Pattern(regexp = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$", message = "手机号不合法")
    private String operatePhoneNumber;
    /**
     * 操作人权限
     */
    @ApiModelProperty(value = "操作人权限")
    private String operatorAuthority;
    /**
     * 操作人姓名
     */
    @ApiModelProperty(value = "操作人姓名")
    private String operatorName;
    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private String companyName;
    /**
     * 操作结果 1:成功 0:失败
     */
    @ApiModelProperty(value = "操作结果 1:成功 0:失败")
    private String operateResult;
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

