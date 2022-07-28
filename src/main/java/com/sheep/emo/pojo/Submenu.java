package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Getter
@Setter
@ToString
public class Submenu implements Serializable {
    private static final long serialVersionUID = 877080340343359912L;
    /**
     * submenu_id
     */
    @TableId
    @JSONField(serialize = false)
    private Long submenuId;
    /**
     * 用户查看
     */
    private String submenuTitle;
    /**
     * children_id
     */
    @JSONField(serialize = false)
    private Long childrenId;
    /**
     * /sys_user_view
     */
    private String path;
    /**
     * /system/sys-user-view
     */
    private String component;

}

