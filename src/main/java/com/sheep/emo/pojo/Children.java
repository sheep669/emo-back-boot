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
 * children(Children)实体类
 *
 * @author makejava
 * @since 2022-07-16 10:45:56
 */
@JsonIgnoreProperties({"childrenId", "menuId"})
@Getter
@Setter
@ToString
public class Children implements Serializable {
    private static final long serialVersionUID = 230548543647678837L;
    /**
     * 子id
     */
    @TableId(type = IdType.AUTO)
    private Long childrenId;
    /**
     * 子菜单详细标题
     */
    private String detailTitle;
    /**
     * 主菜单id
     */
    private Long menuId;
    /**
     * el-divider是否隐藏
     */
    private String hidden;

    private List<Submenu> submenu;
}

