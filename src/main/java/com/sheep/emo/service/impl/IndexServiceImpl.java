package com.sheep.emo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sheep.emo.mapper.GroupBuyingOrganizerMapper;
import com.sheep.emo.mapper.MemberMapper;
import com.sheep.emo.mapper.UserMapper;
import com.sheep.emo.pojo.User;
import com.sheep.emo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : sheep669
 * @description : TODO
 * @created at 2022/9/8 8:26
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupBuyingOrganizerMapper groupBuyingOrganizerMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public int getMemberNum() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Long userNum = userMapper.selectCount(queryWrapper);
        return -1;
    }
}

