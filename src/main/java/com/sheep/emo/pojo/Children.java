package com.sheep.emo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * children(Children)实体类
 *
 * @author makejava
 * @since 2022-07-16 10:45:56
 */
@Getter
@Setter
@ToString
public class Children implements Serializable {
    private static final long serialVersionUID = 230548543647678837L;
    /**
     * children_id
     */
    @TableId
    @JSONField(serialize = false)
    private Long childrenId;
    /**
     * 系统用户管理
     */
    private String detailTitle;
    /**
     * root_id
     */
    @JSONField(serialize = false)
    private Long menuId;

    private String hidden;

    private List<Submenu> submenu;
}

