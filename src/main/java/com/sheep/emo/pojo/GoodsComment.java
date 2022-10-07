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
 * 商品评论表(GoodsComment)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
@ApiModel(value = "GoodsComment对象", description = "商品评论表")
public class GoodsComment implements Serializable {
    private static final long serialVersionUID = -48033579223697267L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 买家信息
     */
    @ApiModelProperty(value = "买家信息")
    private String buyerInformation;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;
    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    /**
     * 评论图片
     */
    @ApiModelProperty(value = "评论图片")
    private String commentImage;
    /**
     * 审核状态 1:待审核  2:审核中 3:通过 4:不予通过
     */
    @ApiModelProperty(value = "审核状态<br>1:待审核  2:审核中 3:通过 4:不予通过")
    private String auditStatus;
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

