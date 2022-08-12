package com.sheep.emo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * submenu(Submenu)实体类
 *
 * @author makejava
 * @since 2022-07-16 10:45:56
 */
@JsonIgnoreProperties({"submenuId", "childrenId"})
@Getter
@Setter
@ToString
public class Submenu implements Serializable {
    private static final long serialVersionUID = 877080340343359912L;
    /**
     * 子菜单id
     */
    @TableId(type = IdType.AUTO)
    private Long submenuId;
    /**
     * 子菜单标题
     */
    private String submenuTitle;
    /**
     * 子id
     */
    private Long childrenId;
    /**
     * 子路由路径
     */
    private String path;
    /**
     * 子路由组件
     */
    private String component;

}

