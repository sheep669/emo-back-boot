package com.sheep.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.emo.pojo.Menu;

import java.util.List;

/**
 * @author sheep669
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * TODO
     *
     * @param role 角色
     * @return java.util.List<com.example.demo.pojo.Menu>
     * @author sheep669
     * @created at 2022/7/18 10:39
     */
    List<Menu> getMenuByRole(String role);
}