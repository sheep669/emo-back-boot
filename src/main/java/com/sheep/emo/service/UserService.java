package com.sheep.emo.service;

import com.sheep.emo.pojo.User;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/8/18 21:11
 */
public interface UserService {
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

