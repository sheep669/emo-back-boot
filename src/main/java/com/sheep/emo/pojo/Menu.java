package com.sheep.emo.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Getter
@Setter
@ToString
public class Menu implements Serializable {
    private static final long serialVersionUID = -19332328656835458L;
    /**
     * menu_id
     */
    @TableId
    @JSONField(serialize = false)
    private Long menuId;
    /**
     * path
     */
    private String path;
    /**
     * name
     */
    private String name;
    /**
     * /component
     */
    private String component;
    /**
     * title
     */
    private String title;
    /**
     * icon
     */
    private String icon;
    @JSONField(serialize = false)
    private String role;

    private List<Children> children;
}

