package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 配送路线表(DeliveryRoutes)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class DeliveryRoutes implements Serializable {
    private static final long serialVersionUID = -59920689200716324L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 路线名称
     */
    private String deliveryRoute;
    /**
     * 配送员
     */
    private String deliveryMan;
    /**
     * 联系电话
     */
    @Pattern(regexp = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$", message = "手机号不合法")
    private String phoneNumber;
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

