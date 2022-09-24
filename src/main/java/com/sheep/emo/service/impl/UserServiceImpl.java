package com.sheep.emo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.UserMapper;
import com.sheep.emo.pojo.User;
import com.sheep.emo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

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
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public User findPersonalInformation(String username) {
        return userMapper.findPersonalInformation(username);
    }

    @Override
    public Page<User> searchOrGetUserList(int current, int size, User user) {
        Page<User> page = new Page<>(current, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(user)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(user.getId()) && user.getId() != 0) {
                queryWrapper.eq("id", user.getId());
            }
            if (StrUtil.isNotBlank(user.getUsername())) {
                queryWrapper.eq("username", user.getUsername());
            }
            if (StrUtil.isNotBlank(user.getPassword())) {
                queryWrapper.eq("password", user.getPassword());
            }
            if (StrUtil.isNotBlank(user.getRole())) {
                queryWrapper.eq("role", user.getRole());
            }
            if (StrUtil.isNotBlank(user.getUserType())) {
                queryWrapper.eq("user_type", user.getUserType());
            }
            if (StrUtil.isNotBlank(user.getAvatar())) {
                queryWrapper.eq("avatar", user.getAvatar());
            }
            if (StrUtil.isNotBlank(user.getPhoneNumber())) {
                queryWrapper.eq("phone_number", user.getPhoneNumber());
            }
            if (ObjectUtil.isNotNull(user.getAccountBalance()) && !BigDecimal.ZERO.equals(user.getAccountBalance())) {
                queryWrapper.eq("account_balance", user.getAccountBalance());
            }
            if (StrUtil.isNotBlank(user.getLocation())) {
                queryWrapper.eq("location", user.getLocation());
            }
            if (StrUtil.isNotBlank(user.getAddress())) {
                queryWrapper.eq("address", user.getAddress());
            }
            if (ObjectUtil.isNotNull(user.getRegisterTime())) {
                queryWrapper.eq("register_time", user.getRegisterTime());
            }
            if (StrUtil.isNotBlank(user.getStatus())) {
                queryWrapper.eq("status", user.getStatus());
            }
            return userMapper.selectPage(page, queryWrapper);
        }
        return userMapper.selectPage(page, null);
    }


    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteUserBatchByIds(Long[] ids) {
        return userMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateUserById(User user, Long id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);
        return userMapper.update(user, wrapper);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

}

