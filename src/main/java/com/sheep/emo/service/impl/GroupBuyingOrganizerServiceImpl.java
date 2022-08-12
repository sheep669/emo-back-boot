package com.sheep.emo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author : sheep669
 * @description : 团长的增删改查服务层接口的实现类
 * @created at 2022/7/29 11:36
 */
@Service
public class GroupBuyingOrganizerServiceImpl implements GroupBuyingOrganizerService {

    @Autowired
    private GroupBuyingOrganizerMapper groupBuyingOrganizerMapper;

    @Override
    public Page<GroupBuyingOrganizer> searchOrGetGroupBuyingOrganizerList(int current, int size, GroupBuyingOrganizer groupBuyingOrganizer) {
        Page<GroupBuyingOrganizer> page = new Page<>(current, size);
        QueryWrapper<GroupBuyingOrganizer> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(groupBuyingOrganizer)) {
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getRecommendGroupBuyingOrganizer())) {
                queryWrapper.like("recommend_group_buying_organizer", groupBuyingOrganizer.getRecommendGroupBuyingOrganizer());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getReceiverAddress())) {
                queryWrapper.like("receiver_address", groupBuyingOrganizer.getReceiverAddress());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getPhoneNumber())) {
                queryWrapper.eq("phone_number", groupBuyingOrganizer.getPhoneNumber());
            }
            return groupBuyingOrganizerMapper.selectPage(page, queryWrapper);
        }
        return groupBuyingOrganizerMapper.selectPage(page, null);
    }

    @Override
    public int deleteGroupBuyingOrganizerById(Long id) {
        return groupBuyingOrganizerMapper.deleteById(id);
    }

    @Override
    public int deleteGroupBuyingOrganizerBatchByIds(Long[] ids) {
        return groupBuyingOrganizerMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGroupBuyingOrganizerById(GroupBuyingOrganizer groupBuyingOrganizer, Long id) {
        LambdaUpdateWrapper<GroupBuyingOrganizer> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(GroupBuyingOrganizer::getId, id);
        return groupBuyingOrganizerMapper.update(groupBuyingOrganizer, wrapper);
    }

    @Override
    public int addGroupBuyingOrganizer(GroupBuyingOrganizer groupBuyingOrganizer) {
        return groupBuyingOrganizerMapper.insert(groupBuyingOrganizer);
    }
}

