package com.sheep.emo.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.UserTypeMapper;
import com.sheep.emo.pojo.UserType;
import com.sheep.emo.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 操作用户类型的服务实现类
 *
 * @author sheep669
 * @created at 2022-08-23
 */

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeMapper userTypeMapper;


    @Override
    public Page<UserType> searchOrGetUserTypeList(int current, int size, UserType userType) {
        Page<UserType> page = new Page<>(current, size);
        QueryWrapper<UserType> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(userType)) {
            // 按需添加过滤条件     模糊查询 like  查询 eq
            if (ObjectUtil.isNotNull(userType.getId()) && userType.getId() != 0) {
                queryWrapper.eq("id", userType.getId());
            }
            if (StrUtil.isNotBlank(userType.getUserType())) {
                queryWrapper.eq("user_type", userType.getUserType());
            }
            if (ObjectUtil.isNotNull(userType.getUserNumber()) && userType.getUserNumber() != 0) {
                queryWrapper.eq("user_number", userType.getUserNumber());
            }
            return userTypeMapper.selectPage(page, queryWrapper);
        }
        return userTypeMapper.selectPage(page, null);
    }


    @Override
    public int deleteUserTypeById(Long id) {
        return userTypeMapper.deleteById(id);
    }

    @Override
    public int deleteUserTypeBatchByIds(Long[] ids) {
        return userTypeMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateUserTypeById(UserType userType, Long id) {
        LambdaQueryWrapper<UserType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserType::getId, id);
        return userTypeMapper.update(userType, wrapper);
    }

    @Override
    public int addUserType(UserType userType) {
        return userTypeMapper.insert(userType);
    }

}

