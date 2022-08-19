package com.sheep.emo.service.impl;

import com.sheep.emo.mapper.UserMapper;
import com.sheep.emo.pojo.User;
import com.sheep.emo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/8/18 21:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findPersonalInformation(String username) {
        return userMapper.findPersonalInformation(username);
    }
}

