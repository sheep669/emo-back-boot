package com.sheep.emo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheep.emo.mapper.GroupBuyingOrganizerMapper;
import com.sheep.emo.pojo.GroupBuyingOrganizer;
import com.sheep.emo.service.GroupBuyingOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            if (ObjectUtil.isNotNull(groupBuyingOrganizer.getId()) && groupBuyingOrganizer.getId() != 0) {
                queryWrapper.eq("id", groupBuyingOrganizer.getId());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getPhoneNumber())) {
                queryWrapper.eq("phone_number", groupBuyingOrganizer.getPhoneNumber());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getStoreName())) {
                queryWrapper.like("store_name", groupBuyingOrganizer.getStoreName());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getRecommendGroupBuyingOrganizer())) {
                queryWrapper.like("recommend_group_buying_organizer", groupBuyingOrganizer.getRecommendGroupBuyingOrganizer());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getReceiverAddress())) {
                queryWrapper.like("receiver_address", groupBuyingOrganizer.getReceiverAddress());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getReferrer())) {
                queryWrapper.like("referrer", groupBuyingOrganizer.getReferrer());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getRemark())) {
                queryWrapper.like("remark", groupBuyingOrganizer.getRemark());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizer.getApplyTime())) {
                queryWrapper.eq("apply_time", groupBuyingOrganizer.getApplyTime());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizer.getCommissionBalance()) && !BigDecimal.ZERO.equals(groupBuyingOrganizer.getCommissionBalance())) {
                queryWrapper.eq("commission_balance", groupBuyingOrganizer.getCommissionBalance());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizer.getEarningsBalance()) && !BigDecimal.ZERO.equals(groupBuyingOrganizer.getEarningsBalance())) {
                queryWrapper.eq("earnings_balance", groupBuyingOrganizer.getEarningsBalance());
            }
            if (ObjectUtil.isNotNull(groupBuyingOrganizer.getCashBalance()) && !BigDecimal.ZERO.equals(groupBuyingOrganizer.getCashBalance())) {
                queryWrapper.eq("cash_balance", groupBuyingOrganizer.getCashBalance());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getGroupBuyingOrganizerStatus())) {
                queryWrapper.eq("group_buying_organizer_status", groupBuyingOrganizer.getGroupBuyingOrganizerStatus());
            }
            if (StrUtil.isNotBlank(groupBuyingOrganizer.getAuditStatus())) {
                queryWrapper.eq("audit_status", groupBuyingOrganizer.getAuditStatus());
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

    @Override
    public int confirmAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "2");
        return groupBuyingOrganizerMapper.update(null, updateWrapper);
    }

    @Override
    public int approveAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "3");
        return groupBuyingOrganizerMapper.update(null, updateWrapper);
    }

    @Override
    public int rejectAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "4");
        return groupBuyingOrganizerMapper.update(null, updateWrapper);
    }

    @Override
    public int reAudit(Long id) {
        UpdateWrapper<GroupBuyingOrganizer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("audit_status", "1");
        return groupBuyingOrganizerMapper.update(null, updateWrapper);
    }
}

