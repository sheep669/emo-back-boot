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
 * 会员优惠权限表(MemberDiscountPrivilege)实体类
 *
 * @author makejava
 * @since 2022-08-22 10:57:32
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
public class MemberDiscountPrivilege implements Serializable {
    private static final long serialVersionUID = -93060245691584020L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 优惠类型
     */
    @ApiModelProperty(value = "优惠类型")
    private String discountType;
    /**
     * 标签所属会员id
     */
    @ApiModelProperty(value = "标签所属会员id")
    private Long memberId;
    /**
     * 会员优惠有效状态 1:生效 2:失效 3:已结束
     */
    @ApiModelProperty(value = "会员优惠有效状态 1:生效 2:失效 3:已结束")
    private String memberValidStatus;
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

