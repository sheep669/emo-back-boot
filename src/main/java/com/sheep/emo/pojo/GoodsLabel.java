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
 * 商品标签表(GoodsLabel)实体类
 *
 * @author makejava
 * @since 2022-07-28 21:39:31
 */
@JsonIgnoreProperties({"createTime", "updateTime"})
@Getter
@Setter
@ToString
@ApiModel(value = "GoodsLabel对象", description = "商品标签表")
public class GoodsLabel implements Serializable {
    private static final long serialVersionUID = -75682567951723733L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true)
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称")
    private String labelName;
    /**
     * 标签编码
     */
    @ApiModelProperty(value = "标签编码")
    private Long commodityCode;
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

