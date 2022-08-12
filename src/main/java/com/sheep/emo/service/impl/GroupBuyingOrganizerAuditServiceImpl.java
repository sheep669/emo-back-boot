package com.sheep.emo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerAuditMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizerAudit;
import com.sheep.emo.service.GroupBuyingOrganizerAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * TODO
 *
 * @author sheep669
 * @created at 2022-08-12
 */

@Service
public class GroupBuyingOrganizerAuditServiceImpl implements GroupBuyingOrganizerAuditService {

    @Autowired
    private GroupBuyingOrganizerAuditMapper groupBuyingOrganizerAuditMapper;

    @Override
    public Page<GroupBuyingOrganizerAudit> searchOrGetGroupBuyingOrganizerAuditList(int current, int size, GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        Page<GroupBuyingOrganizerAudit> page = new Page<>(current, size);
        //QueryWrapper<GroupBuyingOrganizerAudit> queryWrapper = new QueryWrapper<>();
        // if (ObjectUtil.isNotNull(groupBuyingOrganizer)) {
        //  if (StrUtil.isNotBlank(groupBuyingOrganizer.getRecommendGroupBuyingOrganizer())) {
        //  queryWrapper.like("recommend_group_buying_organizer", groupBuyingOrganizer.getRecommendGroupBuyingOrganizer());
        //  }
        //  if (StrUtil.isNotBlank(groupBuyingOrganizer.getReceiverAddress())) {
        //  queryWrapper.like("receiver_address", groupBuyingOrganizer.getReceiverAddress());
        //  }
        //  if (StrUtil.isNotBlank(groupBuyingOrganizer.getPhoneNumber())) {
        //  queryWrapper.eq("phone_number", groupBuyingOrganizer.getPhoneNumber());
        //  .... 按需添加功能
        //   }
        //  return groupBuyingOrganizerMapper.selectPage(page, queryWrapper);
        //  }
        return groupBuyingOrganizerAuditMapper.selectPage(page, null);
    }


    @Override
    public int deleteGroupBuyingOrganizerAuditById(Long id) {
        return groupBuyingOrganizerAuditMapper.deleteById(id);
    }

    @Override
    public int deleteGroupBuyingOrganizerAuditBatchByIds(Long[] ids) {
        return groupBuyingOrganizerAuditMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateGroupBuyingOrganizerAuditById(GroupBuyingOrganizerAudit groupBuyingOrganizerAudit, Long id) {
        LambdaQueryWrapper<GroupBuyingOrganizerAudit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GroupBuyingOrganizerAudit::getId, id);
        return groupBuyingOrganizerAuditMapper.update(groupBuyingOrganizerAudit, wrapper);
    }

    @Override
    public int addGroupBuyingOrganizerAudit(GroupBuyingOrganizerAudit groupBuyingOrganizerAudit) {
        return groupBuyingOrganizerAuditMapper.insert(groupBuyingOrganizerAudit);
    }

}

