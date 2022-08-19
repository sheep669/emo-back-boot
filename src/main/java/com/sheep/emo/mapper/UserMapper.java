package com.sheep.emo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheep.emo.pojo.User;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/7/17 15:30
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取个人资料页
     *
     * @param username 用户名
     * @return com.sheep.emo.pojo.User
     * @author sheep669
     * @created at 2022/8/18 21:10
     */
    User findPersonalInformation(String username);
}
