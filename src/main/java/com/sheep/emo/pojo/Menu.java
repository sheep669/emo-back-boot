package com.sheep.emo.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * root(Menu)实体类
 *
 * @author makejava
 * @since 2022-07-16 10:45:56
 */
@JsonIgnoreProperties({"menuId", "role"})
@Getter
@Setter
@ToString
public class Menu implements Serializable {
    private static final long serialVersionUID = -19332328656835458L;
    /**
     * 主菜单id
     */
    @TableId(type = IdType.AUTO)
    private Long menuId;
    /**
     * 路由路径
     */
    private String path;
    /**
     * 路由名
     */
    private String name;
    /**
     * 路由组件
     */
    private String component;
    /**
     * 路由标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 角色
     */
    private String role;

    private List<Children> children;
}

