package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserType)实体类
 *
 * @author makejava
 * @since 2022-07-29 09:18:03
 */
@Getter
@Setter
@ToString
public class UserType implements Serializable {
    private static final long serialVersionUID = -46708324391184706L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户类型 1:普通会员 2:超级会员 3:商品会员 4:团长 5:管理员
     */
    private String userType;
    /**
     * 会员数
     */
    private Integer userNumber;
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

